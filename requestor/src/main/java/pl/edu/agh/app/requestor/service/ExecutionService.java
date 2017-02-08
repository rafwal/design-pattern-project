package pl.edu.agh.app.requestor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.requestor.dto.TestExecutionDTO;
import pl.edu.agh.app.requestor.repository.TestExecutionRepository;
import pl.edu.agh.app.requestor.dto.RunTestCallbackDTO;
import pl.edu.agh.app.requestor.dto.ExecuteDTO;
import pl.edu.agh.app.requestor.model.entity.execution.SingleExecution;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;
import pl.edu.agh.app.requestor.model.enums.TestExecutionState;
import pl.edu.agh.app.requestor.plugins.execution.ExecutionPluginsLoader;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.EntityDefinition;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.Plugin;
import pl.edu.agh.app.requestor.plugins.execution.interfaces.Task;
import pl.edu.agh.app.requestor.repository.SingleExecutionRepository;
import pl.edu.agh.app.requestor.repository.TestDefinitionRepository;
import pl.edu.agh.app.requestor.repository.ThreadGroupRepository;
import pl.edu.agh.app.requestor.model.entity.definition.TestDefinition;
import pl.edu.agh.app.requestor.model.entity.definition.ThreadGroup;
import pl.edu.agh.app.acceptor.TimersRegistryAcceptor;
import pl.edu.agh.app.requestor.util.converter.TestExecutionConverter;

import java.time.Instant;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ExecutionService {

    @Autowired
    private TestDefinitionRepository testDefinitionRepository;

    @Autowired
    private ThreadGroupRepository threadGroupRepository;

    @Autowired
    private TestExecutionRepository testExecutionRepository;

    @Autowired
    private SingleExecutionRepository singleExecutionRepository;

    @Autowired
    private ExecutionPluginsLoader executionPluginsLoader;

    @Autowired
    private TimersRegistryAcceptor timersRegistryAcceptor;


    public TestExecutionDTO getTestExecution(Long id) {
        TestExecution testExecution = testExecutionRepository.findById(id).orElseThrow(RuntimeException::new);
        return TestExecutionConverter.toDto(testExecution);
    }


    public RunTestCallbackDTO runTests(ExecuteDTO executeDTO) {

        TestDefinition testDef = getTestDefinition(executeDTO);
        ThreadGroup threadGroup = getThreadGroup(executeDTO);

        Plugin plugin = getPlugin(testDef);
        EntityDefinition entityDefinition = plugin.getEntityDefinition(testDef.getJson());
        Task task = plugin.getTask();

        TestExecution testExecution = buildTestExecution(testDef, threadGroup, executeDTO.getTimeout());
        final TestExecution afterSave = testExecutionRepository.saveAndFlush(testExecution);

        CompletableFuture
                .runAsync(() -> timersRegistryAcceptor.doStartListening(testDef.getAppName()))
                .thenRunAsync(() -> execute(threadGroup, entityDefinition, task, afterSave))
                .thenRunAsync(() -> timersRegistryAcceptor.stopListeningIn(executeDTO.getDelayBeforeClosingAcceptor()))
                .thenRunAsync(() -> timersRegistryAcceptor.processTimers(afterSave, testDef.getAppName()))
                .thenRunAsync(() -> finish(testExecution))
                .thenRun(() -> System.out.println(String.format("TestExecution %s ended", afterSave.getId())));

        return new RunTestCallbackDTO(afterSave.getId());
    }

    private void finish(TestExecution testExecution) {
        testExecution.setState(TestExecutionState.FINISHED);
        testExecutionRepository.saveAndFlush(testExecution);
    }

    private void execute(ThreadGroup threadGroup, EntityDefinition entityDefinition, Task task, TestExecution testExecution) {

        System.out.println("EXECUTING STARTS");

        ExecutorService executorService = Executors.newFixedThreadPool(threadGroup.getThreadsCount());

        List<SingleExecution> singleExecutions = Collections.synchronizedList(new LinkedList<SingleExecution>());

        for (int i=0; i<threadGroup.getThreadsCount(); i++) {
            int threadNo = i+1;
            executorService.submit(() -> {
                for(int j=0; j<threadGroup.getLoopCount(); j++) {
                    SingleExecution se = buildSingleExecution(testExecution, threadNo);

                    String output = task.execute(entityDefinition);

                    se.setEndTime(Instant.now());
                    se.setOutput(output);
                    singleExecutions.add(se);

                    sleep(threadGroup.getDelay());
                }
            });
        }

        executorService.shutdown();
        waitUntilAllThreadsFinishAndGetFinalState(executorService, testExecution.getTimeout());

        testExecution.setState(TestExecutionState.COLLECTING_METRICS);
        testExecution.setEndTime(Instant.now());
        testExecutionRepository.saveAndFlush(testExecution);

        singleExecutionRepository.save(singleExecutions);

        System.out.println("EXECUTING ENDS");
    }


    private void sleep(Integer delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private TestExecutionState waitUntilAllThreadsFinishAndGetFinalState(ExecutorService executorService, Long timeout) {
        try {
            boolean isOk = executorService.awaitTermination(timeout, TimeUnit.MINUTES);
            if (isOk) {
                return TestExecutionState.FINISHED;
            } else {
                return TestExecutionState.TIMED_OUT;
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            return TestExecutionState.ERROR;
        }
    }




    private Plugin getPlugin(TestDefinition testDef) {
        return executionPluginsLoader.findByName(testDef.getPluginName())
                .orElseThrow(RuntimeException::new);
    }


    private ThreadGroup getThreadGroup(ExecuteDTO executeDTO) {
        return threadGroupRepository.findById(executeDTO.getThreadGroupId())
                .orElseThrow(RuntimeException::new);
    }

    private TestDefinition getTestDefinition(ExecuteDTO executeDTO) {
        return testDefinitionRepository.getById(executeDTO.getTestDefinitionId())
                .orElseThrow(RuntimeException::new);
    }


    private SingleExecution buildSingleExecution(TestExecution testExecution, int threadNo) {
        return SingleExecution.builder()
                .startTime(Instant.now())
                .testExecution(testExecution)
                .threadNo(threadNo)
                .build();
    }

    private TestExecution buildTestExecution(TestDefinition testDef, ThreadGroup threadGroup, Long timeout) {
        TestExecution testExecution = TestExecution.builder()
                .timeout(timeout)
                .testDefinition(testDef)
                .threadGroup(threadGroup)
                .state(TestExecutionState.IN_PROGRESS)
                .startTime(Instant.now())
                .build();

        return testExecution;
    }


}

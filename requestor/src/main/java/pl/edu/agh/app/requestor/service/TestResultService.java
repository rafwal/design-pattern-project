package pl.edu.agh.app.requestor.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.requestor.dto.AverageResultsDTO;
import pl.edu.agh.app.requestor.dto.SingleExecutionDTO;
import pl.edu.agh.app.requestor.dto.TimerSimpleDTO;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;
import pl.edu.agh.app.requestor.repository.SingleExecutionRepository;
import pl.edu.agh.app.requestor.repository.TestExecutionRepository;
import pl.edu.agh.app.requestor.repository.TimerRepository;
import pl.edu.agh.app.requestor.util.converter.SingleTestExecutionConverter;
import pl.edu.agh.app.requestor.util.converter.TimerConverterr;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TestResultService {

    @Autowired
    private SingleExecutionRepository singleExecutionRepository;

    @Autowired
    private TimerRepository timerRepository;

    @Autowired
    private TestExecutionRepository testExecutionRepository;

    public DetailedResultsDTO getDetailedResult(Long testExecutionId) {
        DetailedResultsDTO detailedResultsDTO = new DetailedResultsDTO();
        TestExecution testExecution = testExecutionRepository.findById(testExecutionId).orElseThrow(RuntimeException::new);

        detailedResultsDTO.setAppName(testExecution.getTestDefinition().getAppName());
        detailedResultsDTO.setStartTime(testExecution.getStartTime());
        detailedResultsDTO.setEndTime(testExecution.getEndTime());
        detailedResultsDTO.setDuration(Duration.between(testExecution.getStartTime(), testExecution.getEndTime()).toMillis());
        detailedResultsDTO.setSingleExecutions(getSingleExecutionDTOs(testExecutionId));
        detailedResultsDTO.setTimers(getSimpleTimerMultimap(testExecutionId).asMap());

        return detailedResultsDTO;
    }

    public AverageResultsDTO getAverageResults(Long testExecutionId) {
        AverageResultsDTO averageResultsDTO = new AverageResultsDTO();
        TestExecution testExecution = testExecutionRepository.findById(testExecutionId).orElseThrow(RuntimeException::new);

        averageResultsDTO.setAppName(testExecution.getTestDefinition().getAppName());
        averageResultsDTO.setStartTime(testExecution.getStartTime());
        averageResultsDTO.setEndTime(testExecution.getEndTime());
        averageResultsDTO.setDuration(Duration.between(testExecution.getStartTime(), testExecution.getEndTime()).toMillis());

        List<SingleExecutionDTO> singleExecutions = getSingleExecutionDTOs(testExecutionId);
        Long sum = singleExecutions.stream()
                .map(se -> Duration.between(se.getStartTime(), se.getEndTime()).toMillis())
                .reduce(0L, Long::sum);
        averageResultsDTO.setAverageSingleExecution(sum/singleExecutions.size());

        Map<String,Long> averageTimers = new HashMap<>();
        Multimap<String, TimerSimpleDTO> simpleTimerMultimap = getSimpleTimerMultimap(testExecutionId);
        simpleTimerMultimap.keySet().forEach(k -> {
            Collection<TimerSimpleDTO> timerSimpleDTOs = simpleTimerMultimap.get(k);
            Long timerSum = timerSimpleDTOs.stream()
                    .map(t -> Duration.between(t.getStartTime(), t.getEndTime()).toMillis())
                    .reduce(0L, Long::sum);
            averageTimers.put(k,timerSum/timerSimpleDTOs.size());
        });
        averageResultsDTO.setAverageTimers(averageTimers);

        return averageResultsDTO;
    }

    private Multimap<String, TimerSimpleDTO> getSimpleTimerMultimap(Long testExecutionId) {
        Multimap<String, TimerSimpleDTO> timersMultimap = ArrayListMultimap.create();
        timerRepository.getByTestExecutionId(testExecutionId)
                .forEach(t -> timersMultimap.put(t.getName(), TimerConverterr.toSimpleDTO(t)));
        return timersMultimap;
    }

    private List<SingleExecutionDTO> getSingleExecutionDTOs(Long testExecutionId) {
        return singleExecutionRepository.getByTestExecutionId(testExecutionId).stream()
                    .map(SingleTestExecutionConverter::toDTO).collect(Collectors.toList());
    }
}

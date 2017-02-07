package pl.edu.agh.app.requestor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.requestor.dto.ThreadGroupDTO;
import pl.edu.agh.app.requestor.model.entity.definition.TestDefinition;
import pl.edu.agh.app.requestor.model.entity.definition.ThreadGroup;
import pl.edu.agh.app.requestor.repository.TestDefinitionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TestDefinitionService {

    @Autowired
    private TestDefinitionRepository testDefinitionRepository;

    public List<TestDefinition> getAll() {
        return testDefinitionRepository.findAll();
    }


    public void create(TestDefinition testDefinition) {
        testDefinitionRepository.save(testDefinition);
    }

    public TestDefinition getById(Long testDefinitionId) {
        TestDefinition testDefinition = testDefinitionRepository.getById(testDefinitionId).orElseThrow(RuntimeException::new);
        return testDefinition;
    }
}

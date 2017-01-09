package pl.edu.agh.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.model.entity.definition.TestDefinition;
import pl.edu.agh.app.repository.TestDefinitionRepository;

import java.util.List;

@Service
public class TestDefinitionService {

    @Autowired
    private TestDefinitionRepository testDefinitionRepository;

    public List<TestDefinition> getAll() {
        return testDefinitionRepository.findAll();
    }
}

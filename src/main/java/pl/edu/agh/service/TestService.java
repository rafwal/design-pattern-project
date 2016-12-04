package pl.edu.agh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.dto.TestDTO;
import pl.edu.agh.model.definition.Test;
import pl.edu.agh.model.definition.ThreadGroup;
import pl.edu.agh.repository.TestRepository;
import pl.edu.agh.repository.ThreadGroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService {

    @Autowired
    private TestRepository testRepository;
    
    @Autowired
    private ThreadGroupRepository threadGroupRepository;

    public List<TestDTO> getAll() {
        return testRepository.findAll()
                .stream()
                .map(TestDTO::toDTO)
                .collect(Collectors.toList());
    }

    public TestDTO getById(Integer requestId) {
        return testRepository.findById(requestId)
                .map(TestDTO::toDTO)
                .orElseThrow(RuntimeException::new);
    }

    public void create(TestDTO testDTO) {
        ThreadGroup threadGroup = threadGroupRepository
                .findById(testDTO.getThreadGroupId())
                .orElseThrow(RuntimeException::new);

        Test test = TestDTO.toEntity(testDTO);
        test.setThreadGroup(threadGroup);
        testRepository.save(test);
    }
}

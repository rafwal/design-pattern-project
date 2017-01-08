package pl.edu.agh.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.app.model.dto.ThreadGroupDTO;
import pl.edu.agh.app.repository.ThreadGroupRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThreadGroupService {

    @Autowired
    private ThreadGroupRepository threadGroupRepository;

    public void createThreadGroup(ThreadGroupDTO threadGroupDTO) {
        threadGroupRepository.save(ThreadGroupDTO.toEntity(threadGroupDTO));
    }

    public List<ThreadGroupDTO> getAll() {
        return threadGroupRepository.findAll()
                .stream()
                .map(ThreadGroupDTO::toDTO)
                .collect(Collectors.toList());
    }

    public ThreadGroupDTO getById(Integer threadGroupId) {
        return threadGroupRepository.findById(Long.valueOf(threadGroupId))
                .map(ThreadGroupDTO::toDTO)
                .orElseThrow(RuntimeException::new);
    }

}

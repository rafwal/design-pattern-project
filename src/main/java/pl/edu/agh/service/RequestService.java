package pl.edu.agh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.dto.RequestDTO;
import pl.edu.agh.model.Request;
import pl.edu.agh.model.ThreadGroup;
import pl.edu.agh.repository.RequestRepository;
import pl.edu.agh.repository.ThreadGroupRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;
    
    @Autowired
    private ThreadGroupRepository threadGroupRepository;

    public List<RequestDTO> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(RequestDTO::toDTO)
                .collect(Collectors.toList());
    }

    public RequestDTO getById(Integer requestId) {
        return requestRepository.findById(requestId)
                .map(RequestDTO::toDTO)
                .orElse(null);
    }

    public void create(RequestDTO requestDTO) {
        Optional<ThreadGroup> byId = threadGroupRepository.findById(requestDTO.getThreadGroupId());
        if (byId.isPresent()) {
            Request request = RequestDTO.toEntity(requestDTO);
            request.setThreadGroup(byId.get());
            requestRepository.save(request);
        } else {
            //TODO Exception
        }
    }
}

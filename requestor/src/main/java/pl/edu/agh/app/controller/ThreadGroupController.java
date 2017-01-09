package pl.edu.agh.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.util.URL;
import pl.edu.agh.app.model.dto.ThreadGroupDTO;
import pl.edu.agh.app.service.ThreadGroupService;

import java.util.List;


@RestController
public class ThreadGroupController {
    
    @Autowired
    private ThreadGroupService threadGroupService;


    @RequestMapping(method = RequestMethod.GET, value = URL.ThreadGroup.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ThreadGroupDTO> getAll() {
        return threadGroupService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = URL.ThreadGroup.ALL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody ThreadGroupDTO threadGroupDTO) {
        threadGroupService.createThreadGroup(threadGroupDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = URL.ThreadGroup.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public ThreadGroupDTO getById(@PathVariable Integer threadGroupId) {
        return threadGroupService.getById(threadGroupId);
    }

}

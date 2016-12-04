package pl.edu.agh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.dto.TestDTO;
import pl.edu.agh.service.TestService;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(method = RequestMethod.GET, value = URL.Request.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<TestDTO> getAll() {
        return testService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = URL.Request.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    private TestDTO getById(@PathVariable Integer requestId) {
        return testService.getById(requestId);
    }

    @RequestMapping(method = RequestMethod.POST, value = URL.Request.ALL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void create(@RequestBody TestDTO testDTO) {
        testService.create(testDTO);
    }
}

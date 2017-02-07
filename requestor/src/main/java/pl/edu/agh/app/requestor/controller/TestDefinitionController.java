package pl.edu.agh.app.requestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.requestor.dto.ThreadGroupDTO;
import pl.edu.agh.app.requestor.service.TestDefinitionService;
import pl.edu.agh.app.requestor.model.entity.definition.TestDefinition;
import pl.edu.agh.app.requestor.util.URL;

import java.util.List;

@RestController
public class TestDefinitionController {

    @Autowired
    private TestDefinitionService testDefinitionService;

    @RequestMapping(method = RequestMethod.GET, value = URL.Test.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestDefinition> getAll() {
        return testDefinitionService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = URL.Test.ALL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody TestDefinition testDefinition) {
        testDefinitionService.create(testDefinition);
    }

    @RequestMapping(method = RequestMethod.GET, value = URL.Test.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public TestDefinition getById(@PathVariable Long testDefinitionId) {
        return testDefinitionService.getById(testDefinitionId);
    }
}

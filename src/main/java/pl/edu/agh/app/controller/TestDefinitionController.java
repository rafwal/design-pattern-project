package pl.edu.agh.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.model.entity.definition.TestDefinition;
import pl.edu.agh.app.service.TestDefinitionService;
import pl.edu.agh.app.util.URL;

import java.util.List;

@RestController
public class TestDefinitionController {

    @Autowired
    private TestDefinitionService testDefinitionService;

    @RequestMapping(method = RequestMethod.GET, value = URL.Test.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TestDefinition> getAll() {
        return testDefinitionService.getAll();
    }
}

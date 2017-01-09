package pl.edu.agh.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.model.dto.CallbackDTO;
import pl.edu.agh.app.model.dto.ExecuteDTO;
import pl.edu.agh.app.service.ExecutionService;
import pl.edu.agh.app.util.URL;

@RestController
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @RequestMapping(method = RequestMethod.POST, value = URL.EXECUTE)
    public CallbackDTO execute(@RequestBody ExecuteDTO dto) throws InstantiationException, IllegalAccessException {
        return executionService.runTests(dto);
    }
}

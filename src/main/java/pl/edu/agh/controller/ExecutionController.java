package pl.edu.agh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.agh.service.ExecutionService;

@Controller
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @RequestMapping(method = RequestMethod.POST, value = URL.Request.EXECUTE_BY_ID)
    public ResponseEntity<String> execute(@PathVariable Integer requestId) {
        executionService.execute(requestId);
        return null;
    }
}

package pl.edu.agh.app.requestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.requestor.dto.RunTestCallbackDTO;
import pl.edu.agh.app.requestor.dto.ExecuteDTO;
import pl.edu.agh.app.requestor.dto.TestExecutionDTO;
import pl.edu.agh.app.requestor.service.ExecutionService;
import pl.edu.agh.app.requestor.util.URL;

@RestController
public class ExecutionController {

    @Autowired
    private ExecutionService executionService;


    @RequestMapping(method = RequestMethod.POST, value = URL.EXECUTE)
    public RunTestCallbackDTO execute(@RequestBody ExecuteDTO dto) throws InstantiationException, IllegalAccessException {
        return executionService.runTests(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = URL.TestExecution.BY_ID)
    public TestExecutionDTO getById(@PathVariable Long testExecutionId) {
        return executionService.getTestExecution(testExecutionId);
    }

}

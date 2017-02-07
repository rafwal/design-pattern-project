package pl.edu.agh.app.requestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.requestor.dto.AverageResultsDTO;
import pl.edu.agh.app.requestor.service.DetailedResultsDTO;
import pl.edu.agh.app.requestor.service.TestResultService;
import pl.edu.agh.app.requestor.util.URL;

@RestController
public class TestResultController {

    @Autowired
    private TestResultService testResultService;

    @RequestMapping(value = URL.TestExecution.DETAILED_RESULTS, method = RequestMethod.GET)
    public DetailedResultsDTO getDetailedResults(@PathVariable Long testExecutionId) {
        return testResultService.getDetailedResult(testExecutionId);
    }

    @RequestMapping(value = URL.TestExecution.AVERAGE_RESULTS, method = RequestMethod.GET)
    public AverageResultsDTO getAverageResults(@PathVariable Long testExecutionId) {
        return testResultService.getAverageResults(testExecutionId);
    }

}

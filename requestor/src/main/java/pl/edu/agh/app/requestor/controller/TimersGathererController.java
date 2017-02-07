package pl.edu.agh.app.requestor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.app.requestor.service.TimersGathererService;
import pl.edu.agh.app.requestor.util.URL;

@RestController
public class TimersGathererController {

    @Autowired
    private TimersGathererService timersGathererService;

    @RequestMapping(method = RequestMethod.POST, value = URL.TimersGatherer.START)
    public void start() {
        timersGathererService.start();
    }

    @RequestMapping(method = RequestMethod.POST, value = URL.TimersGatherer.STOP)
    public void stop() {
        timersGathererService.stop();
    }
}

package pl.edu.agh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("HI");
    }


}

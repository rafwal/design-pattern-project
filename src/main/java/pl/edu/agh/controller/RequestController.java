package pl.edu.agh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.dto.RequestDTO;
import pl.edu.agh.service.RequestService;

import java.util.List;

@RestController
public class RequestController {

    @Autowired
    private RequestService requestService;

    @RequestMapping(method = RequestMethod.GET, value = URL.Request.ALL, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<RequestDTO> getAll() {
        return requestService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = URL.Request.BY_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    private RequestDTO getById(Integer requestId) {
        return requestService.getById(requestId);
    }

    @RequestMapping(method = RequestMethod.POST, value = URL.Request.ALL, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void create(@RequestBody RequestDTO requestDTO) {
        requestService.create(requestDTO);
    }
}

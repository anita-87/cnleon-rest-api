package com.cnleon.controllers;

import com.cnleon.domains.Swimmer;
import com.cnleon.services.SwimmerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to respond to all the calls that starts by /swimmer
 *
 * Available calls are:
 * <li>/swimmers/{id}</li>
 *
 * Created by anita on 12/10/16.
 */
@RestController
public class SwimmerController {

    /**
     * Logger instance for the controller.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Swimmer service to perform operations
     */
    @Autowired
    private SwimmerService swimmerService;

    /**
     * Method that obtain a swimmer by its identifier in the DB.
     * @param id - the identifier of the swimmer in the DB.
     * @return the JSON representation of the swimmer if its found
     */
    //TODO: handle if the the swimmer is not found in the DB.
    @RequestMapping("/swimmers/{id}")
    public @ResponseBody Swimmer getSwimmer(@PathVariable String id){
        return swimmerService.getSwimmer(id);
    }
}

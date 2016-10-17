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
 * Created by anita on 12/10/16.
 */
@RestController
public class SwimmerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwimmerService swimmerService;

    @RequestMapping("/swimmer/{id}")
    public @ResponseBody Swimmer getSwimmer(@PathVariable String id){
        return swimmerService.getSwimmer(id);
    }
}

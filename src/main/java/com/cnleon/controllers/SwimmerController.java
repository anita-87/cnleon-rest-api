package com.cnleon.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.cnleon.converters.CategoryEnumConverter;
import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.services.SwimmerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Controller to respond to all the calls that starts by /swimmer
 *
 * Available calls are:
 * <li>/swimmers</li>
 * <li>/swimmers/{id}</li>
 * <li>/swimmers/{category}</li>
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEnumConverter());
    }

    @RequestMapping("/swimmers")
    public @ResponseBody Collection<Resource<Swimmer>> getSwimmers(){
        Collection<Swimmer> swimmers = swimmerService.getSwimmers();
        Collection<Resource<Swimmer>> resources = new ArrayList<>();
        swimmers.forEach((swimmer -> resources.add(getSwimmerResource(swimmer))));
        return resources;
    }

    /**
     * Method that obtain a swimmer by its identifier in the DB.
     * @param id - the identifier of the swimmer in the DB.
     * @return the JSON representation of the swimmer if its found
     */
    //TODO: handle if the the swimmer is not found in the DB.
    @RequestMapping("/swimmers/{id}")
    public @ResponseBody
    Resource<Swimmer> getSwimmer(@PathVariable String id){
        Swimmer swimmer = swimmerService.getSwimmer(id);
        return getSwimmerResource(swimmer);
    }

    /**
     * Method to return all the swimmers from a category
     * @param category - the category to search swimmers from
     * @return a list with all the swimmers from that category
     */
    @RequestMapping("/swimmers/category/{category}")
    public @ResponseBody List<Swimmer> getSwimmersByCategory(@PathVariable Category category){
        return swimmerService.getSwimmersByCategory(category);
    }

    /**
     * Method to construct a resource for a single swimmer.
     * @param swimmer - the swimmer to construct the resource from.
     * @return the resource constructed for the swimmer.
     */
    private Resource<Swimmer> getSwimmerResource(Swimmer swimmer){
        Resource<Swimmer> resource = new Resource<>(swimmer);

        //Link to swimmer (self)
        resource.add(linkTo(methodOn(SwimmerController.class).getSwimmer(swimmer.getId())).withSelfRel());

        return resource;
    }
}

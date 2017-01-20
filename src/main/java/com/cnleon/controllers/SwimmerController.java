package com.cnleon.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.cnleon.converters.CategoryEnumConverter;
import com.cnleon.converters.SwimmerToSwimmerResponseConverter;
import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.mappers.responses.SwimmerResponse;
import com.cnleon.services.SwimmerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SwimmerController {

    /**
     * Logger instance for the controller.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Swimmer service to perform operations.
     */
    @Autowired
    private SwimmerService swimmerService;

    /**
     * Component to convert Swimmers to SwimmerResponse.
     */
    @Autowired
    private SwimmerToSwimmerResponseConverter swimmerToSwimmerResponseConverter;

    /**
     * Initialization of the web data binder for the SwimmerController.
     * @param binder - WebDataBinder to bind elements to.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Category.class, new CategoryEnumConverter());
    }

    /**
     * Method that returns all the swimmers available.
     * @return the JSON response that corresponds with a collection of
     * resources of type SwimmerResponse (small sample of a swimmer info)
     */
    @RequestMapping("/swimmers")
    public Collection<Resource<SwimmerResponse>> getSwimmers(){
        List<Swimmer> swimmers = swimmerService.getSwimmers();
        Collection<SwimmerResponse> swimmerResponses =
                swimmerToSwimmerResponseConverter.swimmerListToSwimmerResponseList(swimmers);
        Collection<Resource<SwimmerResponse>> resources = new ArrayList<>();
        swimmerResponses.forEach((swimmerResponse -> resources.add(getSwimmerResponseResource(swimmerResponse))));
        return resources;
    }

    /**
     * Method that obtain a swimmer by its identifier in the DB.
     * @param id - the identifier of the swimmer in the DB.
     * @return the JSON representation of the swimmer if its found
     */
    //TODO: handle if the the swimmer is not found in the DB.
    @RequestMapping("/swimmers/{id}")
    public Resource<Swimmer> getSwimmer(@PathVariable String id){
        Swimmer swimmer = swimmerService.getSwimmer(id);
        //TODO: handle if the the swimmer is not found in the DB.
        //TODO: using a exception???
        return getSwimmerResource(swimmer);
    }

    /**
     * Method to return all the swimmers from a category
     * @param category - the category to search swimmers from
     * @return a list with all the swimmers from that category
     */
    @RequestMapping("/swimmers/category/{category}")
    public Collection<Resource<SwimmerResponse>> getSwimmersByCategory(@PathVariable Category category){
        List<Swimmer> swimmers = swimmerService.getSwimmersByCategory(category);
        Collection<SwimmerResponse> swimmerResponses =
                swimmerToSwimmerResponseConverter.swimmerListToSwimmerResponseList(swimmers);
        Collection<Resource<SwimmerResponse>> resources = new ArrayList<>();
        swimmerResponses.forEach((swimmerResponse -> resources.add(getSwimmerResponseResource(swimmerResponse))));
        return resources;
    }

    /**
     * Method to save a swimmer instance into the database.
     * @param swimmer - the swimmer to be stored.
     * @return the stored swimmer.
     */
    @RequestMapping(value = "/swimmers", method = RequestMethod.POST)
    public ResponseEntity<Resource<Swimmer>> saveSwimmer(@RequestBody Swimmer swimmer) {
        swimmer = swimmerService.saveSwimmer(swimmer);
        return new ResponseEntity<>(this.getSwimmerResource(swimmer), HttpStatus.CREATED);
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

    /**
     * Method to construct a resource for a SwimmerResponse
     * @param swimmerResponse - the SwimmerResponse to construct the resource from.
     * @return the resource constructed for the swimmer.
     */
    private Resource<SwimmerResponse> getSwimmerResponseResource(SwimmerResponse swimmerResponse) {
        Resource<SwimmerResponse> resource = new Resource<>(swimmerResponse);
        //Link to swimmer (self)
        resource.add(linkTo(methodOn(SwimmerController.class).getSwimmer(swimmerResponse.getId())).withSelfRel());
        return resource;
    }
}

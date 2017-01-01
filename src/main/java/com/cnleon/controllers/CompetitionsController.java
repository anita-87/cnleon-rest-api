package com.cnleon.controllers;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import com.cnleon.converters.CompetitionTypesEnumConverter;
import com.cnleon.domains.League;
import com.cnleon.domains.Round;
import com.cnleon.enumerates.CompetitionTypes;
import com.cnleon.services.CompetitionService;
import com.cnleon.services.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Controller to respond to all the calls that starts by /competitions
 *
 * Available calls are:
 *  <li>/competitions/types</li>
 *  <li>/competitions/{type}</li>
 *  <li>/competitions/league/{id}</li>
 *  <li>/competitions/league/{leagueId}/round/{roundId}</li>
 * Created by anita on 28/11/16.
 */
@RestController
public class CompetitionsController {

    /**
     * Competitions service to perform operations.
     */
    @Autowired
    private CompetitionService competitionsService;

    /**
     * Round service to perform operations.
     */
    @Autowired
    private RoundService roundService;

    /**
     * Initialization of the web data binder for the CompetitionsController.
     * @param binder - WebDataBinder to bind elements to.
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(CompetitionTypes.class, new CompetitionTypesEnumConverter());
    }

    /**
     * Method that returns all the types of competitions available in the API.
     * @return a List with the type of competitions in the API.
     */
    @RequestMapping(value = "/competitions/types", method = RequestMethod.GET)
    public @ResponseBody List<CompetitionTypes> getTypesCompetitions(){
        return Arrays.asList(CompetitionTypes.values());
    }

    /**
     * Method that returns all the competitions given a competition type.
     * @param competitionType - the type of competition to find.
     * @return the JSON response of a Collection with the competitions
     * of tye type passed via the parameter in the call.
     */
    @RequestMapping(value = "/competitions/{type}", method = RequestMethod.GET)
    public @ResponseBody Collection<Object> getCompetitionsByType(@PathVariable(name = "type") CompetitionTypes competitionType) {
        Collection<Object> result = new ArrayList();
        switch (competitionType) {
            case LEAGUE:
                Collection<League> leagues = this.getAllLeagues();
                Collection<Object> resources = new ArrayList<>();
                leagues.forEach((league -> resources.add(this.getLeagueResource(league))));
                result.add(resources);
                break;
            case CHAMPIONSHIP:
                //TODO
                break;
            case TROPHY:
                //TODO
                break;
            default:
                //TODO
                break;
        }
        return result;
    }

    /**
     * Method that returns all the information for a league given its 'id'.
     * @param id - the 'id' of the league to search.
     * @return the JSON response that corresponds with all the information for a league.
     */
    @RequestMapping(value = "/competitions/league/{id}", method = RequestMethod.GET)
    public @ResponseBody League getLeague(@PathVariable String id){
        return  this.competitionsService.getLeague(id);
    }

    /**
     * Method that returns all the information for a round given its 'id'
     * and the 'id' of the league to which the round corresponds to.
     * @param leagueId - the identifier of the league to obtain the round.
     * @param roundId - the identifier of the round of a league.
     * @return the JSON response that corresponds with all the information for a round from a league.
     */
    @RequestMapping(value = "/competitions/league/{leagueId}/round/{roundId}", method = RequestMethod.GET)
    public @ResponseBody Round getLeagueRound(@PathVariable String leagueId, @PathVariable String roundId) {
        return roundService.getRound(leagueId, roundId);
    }

    /**
     * Method that retrieves all the leagues.
     * @return List with all the competitions of type league, available.
     */
    private List<League> getAllLeagues() {
        return competitionsService.getAllLeagues();
    }

    /**
     * Method that given a League returns its resource representation.
     * @param league - the League instance to be converted to a resource.
     * @return a Resource that represents the League information.
     */
    private Resource<League> getLeagueResource(League league) {
        Resource<League> resource = new Resource<>(league);

        //Link to league (self)
        resource.add(linkTo(methodOn(CompetitionsController.class).getLeague(league.getId())).withSelfRel());
        //Link to rounds
        Iterator<Round> roundIterator = league.getRounds().iterator();
        int index = 0;
        while (roundIterator.hasNext()){
            Round round = roundIterator.next();
            index++;
            resource.add(linkTo(methodOn(CompetitionsController.class).getLeagueRound(league.getId(), round.getId())).withRel("round"+index));
        }
        return  resource;
    }
}

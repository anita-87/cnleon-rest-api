package com.cnleon.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.Date;

/**
 * Domain class representing a round entity.
 *
 * Created by anita on 29/11/16.
 */
@Document
public class Round {

    /**
     * Id of the round in the DB.
     */
    @Id
    private String id;
    /**
     * The name of the round.
     */
    private String name;
    /**
     * The place where the round takes place.
     */
    private String place;
    /**
     * The Date that represents the start of the warm up.
     */
    private Date dateWarmup;
    /**
     * The Date that represents the start of the competition.
     */
    private Date dateCompetition;
    /**
     * The Url for the start list of the round.
     */
    private URL startList;
    /**
     * The Url for the results of the round.
     */
    private URL results;

    /**
     * Getter for the "id" property.
     * @return the identifier of the round.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the "id" property.
     * @param id - the identifier of the round to be assigned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the "name" property.
     * @return the name of the round.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the "name" property.
     * @param name - the name of the league to be assigned.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the "place" property.
     * @return the place where the round takes place.
     */
    public String getPlace() {
        return place;
    }

    /**
     * Setter for the "place" property.
     * @param place - the place where the round takes place to be assigned.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Getter for the "dateWarmup" property.
     * @return the date when the warm up starts.
     */
    public Date getDateWarmup() {
        return dateWarmup;
    }

    /**
     * Setter for the "dateWarmup" property.
     * @param dateWarmup - the date when the warm up starts to be assigned.
     */
    public void setDateWarmup(Date dateWarmup) {
        this.dateWarmup = dateWarmup;
    }

    /**
     * Getter for the "dateCompetition" property.
     * @return the date when the competition starts.
     */
    public Date getDateCompetition() {
        return dateCompetition;
    }

    /**
     * Setter for the "dateCompetition" property.
     * @param dateCompetition - the date when the competitions starts to be assigned.
     */
    public void setDateCompetition(Date dateCompetition) {
        this.dateCompetition = dateCompetition;
    }

    /**
     * Getter for the "startList" property.
     * @return the start list url.
     */
    public URL getStartList() {
        return startList;
    }

    /**
     * Setter for the "startList" property.
     * @param startList - the start list for the round to be assigned.
     */
    public void setStartList(URL startList) {
        this.startList = startList;
    }

    /**
     * Getter for the "results" property.
     * @return the results url.
     */
    public URL getResults() {
        return results;
    }

    /**
     * Setter for the "results" property.
     * @param results - the results url for the round to be assigned.
     */
    public void setResults(URL results) {
        this.results = results;
    }
}

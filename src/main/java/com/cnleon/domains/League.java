package com.cnleon.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.net.URL;
import java.util.List;

/**
 * Domain class representing a league entity.
 *
 * Created by anita on 28/11/16.
 */
@Document
public class League {

    /**
     * Id of the league in the DB.
     */
    @Id
    private String id;
    /**
     * Name of the league.
     */
    private String name;
    /**
     * Url that contains the league normative.
     */
    private URL normative;
    /**
     * Url that contains the league results.
     */
    private URL results;
    /**
     * List of rounds for a league.
     */
    private List<Round> rounds;

    /**
     * Getter for the "id" property.
     * @return the identifier of the league.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the "id" property.
     * @param id - the identifier of the league to be assigned.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the "name" property.
     * @return the name of the league.
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
     * Getter for the "normative" property.
     * @return the normative of the league.
     */
    public URL getNormative() {
        return normative;
    }

    /**
     * Setter for the "normative" property.
     * @param normative - the normative of the league to be assigned.
     */
    public void setNormative(URL normative) {
        this.normative = normative;
    }

    /**
     * Getter for the "results" property.
     * @return the results of the league.
     */
    public URL getResults() {
        return results;
    }

    /**
     * Setter for the "results" property.
     * @param results - the results of the league to be assigned.
     */
    public void setResults(URL results) {
        this.results = results;
    }

    /**
     * Getter for the "rounds" property.
     * @return the rounds of the league.
     */
    public List<Round> getRounds() {
        return rounds;
    }

    /**
     * Setter for the "rounds" property.
     * @param rounds - the round of the league to be assigned.
     */
    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
}

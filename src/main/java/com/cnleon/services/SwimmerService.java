package com.cnleon.services;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;

import java.util.List;

/**
 * Swimmer service to perform all needed operations related to swimmers.
 *
 * Created by anita on 12/10/16.
 */
public interface SwimmerService {

    /**
     * Returns all the swimmers available in the DB.
     * @return list of all the swimmers available or an empty list if there are any.
     */
    List<Swimmer> getSwimmers();

    /**
     * Method to return all the swimmers from a category
     * @param category - the category to search swimmers from
     * @return a list with all the swimmers from that category
     */
    List<Swimmer> getSwimmersByCategory(Category category);

    /**
     * Returns a swimmer by its identifier
     * @param id - the identifier of the swimmer in the DB.
     * @return the swimmer instance or null if the swimmer is not found.
     */
    Swimmer getSwimmer(String id);

    /**
     * Saves a swimmer object to the DB.
     * @param swimmer - the swimmer to be stored.
     * @return the saved swimmer object.
     */
    Swimmer saveSwimmer(Swimmer swimmer);
}

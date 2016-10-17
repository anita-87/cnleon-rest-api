package com.cnleon.services;

import com.cnleon.domains.Swimmer;
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
     * Returns a swimmer by its identifier
     * @param id - the identifier of the swimmer in the DB.
     * @return the swimmer instance or null if the swimmer is not found.
     */
    Swimmer getSwimmer(String id);
}

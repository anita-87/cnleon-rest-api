package com.cnleon.services;

import com.cnleon.domains.Swimmer;

/**
 * Swimmer service to perform all needed operations related to swimmers.
 *
 * Created by anita on 12/10/16.
 */
public interface SwimmerService {

    /**
     * Returns a swimmer by its identifier
     * @param id - the identifier of the swimmer in the DB.
     * @return the swimmer instance or null if the swimmer is not found.
     */
    Swimmer getSwimmer(String id);
}

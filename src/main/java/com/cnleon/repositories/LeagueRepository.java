package com.cnleon.repositories;

import com.cnleon.domains.League;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access to the League entities stored in the DB.
 * @see org.springframework.data.mongodb.repository.MongoRepository
 *
 * Created by anita on 28/11/16.
 */
@Repository
public interface LeagueRepository extends MongoRepository<League, String> {

    /**
     * Method to find a league by its identifier.
     * @param id - the identifier of the league.
     * @return a league instance if its found or null if is not.
     */
    League findById(String id);
}

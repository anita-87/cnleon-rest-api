package com.cnleon.repositories;

import com.cnleon.domains.Swimmer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to access to the Swimmer entities stored in the DB.
 * @see org.springframework.data.mongodb.repository.MongoRepository
 *
 * Created by anita on 12/10/16.
 */
@Repository
public interface SwimmerRepository extends MongoRepository<Swimmer, String> {

    /**
     * Method to find a swimmer by its identifier.
     * @param id - the identifier of the swimmer.
     * @return a swimmer instance if its found or null if is not.
     */
    Swimmer findById(String id);
}

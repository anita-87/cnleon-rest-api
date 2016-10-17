package com.cnleon.repositories;

import com.cnleon.domains.Swimmer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by anita on 12/10/16.
 */
@Repository
public interface SwimmerRepository extends MongoRepository<Swimmer, String> {

    Swimmer findById(String id);
}

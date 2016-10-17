package com.cnleon.services.serviceImpl;

import com.cnleon.domains.Swimmer;
import com.cnleon.repositories.SwimmerRepository;
import com.cnleon.services.SwimmerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Implementation of the Swimmer Service Interface to perform operations on swimmers
 * @see com.cnleon.services.SwimmerService
 *
 * Created by anita on 12/10/16.
 */
@Service
public class SwimmerServiceImpl implements SwimmerService {

    /**
     * Logger instance for the service.
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Repository to perform data operations.
     */
    @Autowired
    private SwimmerRepository swimmerRepository;

    /**
     * Returns a swimmer by its identifier
     * @param id - the identifier of the swimmer in the DB.
     * @return the swimmer instance or null if the swimmer is not found.
     */
    @Override
    public Swimmer getSwimmer(String id) {
        logger.info("Searching for swimmer with id '"+id+"'");
        return swimmerRepository.findById(id);
    }
}

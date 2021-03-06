package com.cnleon.services.serviceImpl;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import com.cnleon.repositories.SwimmerRepository;
import com.cnleon.services.SwimmerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
     * Returns all the swimmers available in the DB.
     * @return list of all the swimmers available or an empty list if there are any.
     */
    @Override
    public List<Swimmer> getSwimmers() {
        logger.info("Returning all the swimmers in the DB.");
        return swimmerRepository.findAll();
    }

    /**
     * Method to return all the swimmers from a category
     * @param category - the category to search swimmers from
     * @return a list with all the swimmers from that category
     */
    @Override
    public List<Swimmer> getSwimmersByCategory(Category category) {
        logger.info("Searching for all the swimmers in the DB in " + category + " category");
        return swimmerRepository.findAllByCategory(category);
    }

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

    /**
     * Saves a swimmer object to the DB.
     * @param swimmer - the swimmer to be stored.
     * @return the saved swimmer object.
     */
    @Override
    public Swimmer saveSwimmer(Swimmer swimmer) {
        logger.info("Saving new swimmer with licence '"+swimmer.getLicence()+"' into the DB.");
        return swimmerRepository.save(swimmer);
    }
}

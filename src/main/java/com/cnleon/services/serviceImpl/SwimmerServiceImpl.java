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
 * Created by anita on 12/10/16.
 */
@Service
public class SwimmerServiceImpl implements SwimmerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SwimmerRepository swimmerRepository;

    @Override
    public Swimmer getSwimmer(String id) {
        logger.info("Searching for swimmer with id '"+id+"'");
        return swimmerRepository.findById(id);
    }
}

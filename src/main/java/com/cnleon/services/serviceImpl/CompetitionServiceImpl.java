package com.cnleon.services.serviceImpl;

import com.cnleon.domains.League;
import com.cnleon.repositories.LeagueRepository;
import com.cnleon.services.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by anita on 28/11/16.
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    public List<League> getAllLeagues() {
        logger.info("Searching for all the competitions of type 'League' on the DB.");
        List<League> result = leagueRepository.findAll();
        if (result != null)
            return result;
        else
            return new ArrayList<>();
    }

    @Override
    public League getLeague(String id) {
        logger.info("Searching for 'league' competition with id: '"+id+"'");
        return leagueRepository.findById(id);
    }
}

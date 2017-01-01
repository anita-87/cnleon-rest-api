package com.cnleon.services.serviceImpl;

import com.cnleon.domains.League;
import com.cnleon.domains.Round;
import com.cnleon.repositories.LeagueRepository;
import com.cnleon.services.RoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * Created by anita on 6/12/16.
 */

@Component
public class RoundServiceImpl implements RoundService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    public Round getRound(String leagueId, String roundId) {
        logger.info("Searching for round with id '"+roundId+"' in league with id '"+leagueId+"'");
        Round round = new Round();
        League league = leagueRepository.findById(leagueId);
        if (league != null){
            round = this.getRoundInLeague(league, roundId);
        }
        return round;
    }

    private Round getRoundInLeague(League league, String roundId) {
        Round round = new Round();
        Iterator iterator = league.getRounds().iterator();
        while (iterator.hasNext()) {
            Round currentRound = (Round) iterator.next();
            if (currentRound.getId().equals(roundId)){
                round = currentRound;
                break;
            }
        }
        return round;
    }
}

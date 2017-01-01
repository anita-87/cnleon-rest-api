package com.cnleon.services;

import com.cnleon.domains.Round;

/**
 * Created by anita on 6/12/16.
 */
public interface RoundService {

    Round getRound(String leagueId, String roundId);
}

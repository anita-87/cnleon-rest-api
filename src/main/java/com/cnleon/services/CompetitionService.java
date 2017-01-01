package com.cnleon.services;

import com.cnleon.domains.League;

import java.util.List;

/**
 * Created by anita on 28/11/16.
 */
public interface CompetitionService {

    List<League> getAllLeagues();

    League getLeague(String id);
}

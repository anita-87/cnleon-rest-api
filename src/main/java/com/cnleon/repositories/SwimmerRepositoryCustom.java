package com.cnleon.repositories;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;

import java.util.List;

/**
 * Interface for the custom methods of the SwimmerRepository.
 *
 * Created by anita on 18/10/16.
 */
public interface SwimmerRepositoryCustom {

    /**
     * Method to search for all the swimmers from a given category.
     * @param category - the category to search swimmers.
     * @return a list of swimmers from the category or an empty array if none is found.
     */
    List<Swimmer> findAllByCategory(Category category);

}

package com.cnleon.repositories;

import com.cnleon.domains.Swimmer;
import com.cnleon.enumerates.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Implementation of the Custom SwimmerRepository interface.
 *
 * Created by anita on 18/10/16.
 */
@Component
public class SwimmerRepositoryImpl implements SwimmerRepositoryCustom {

    /**
     * MongoTemplate to do custom queries against the DB.
     */
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Method to search for all the swimmers from a given category.
     * @param category - the category to search swimmers.
     * @return a list of swimmers from the category or an empty array if none is found.
     */
    @Override
    public List<Swimmer> findAllByCategory(Category category) {
        List<Swimmer> swimmers = mongoTemplate.findAll(Swimmer.class);
        List<Swimmer> filteredSwimmers = new ArrayList<Swimmer>();
        Iterator<Swimmer> swimmerIterator = swimmers.iterator();
        while (swimmerIterator.hasNext()){
            Swimmer swimmer = swimmerIterator.next();
            if (swimmer.getCategory().equals(category))
                filteredSwimmers.add(swimmer);
        }
        return filteredSwimmers;
    }

}

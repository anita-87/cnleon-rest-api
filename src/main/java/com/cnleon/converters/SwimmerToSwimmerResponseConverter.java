package com.cnleon.converters;

import com.cnleon.domains.Swimmer;
import com.cnleon.mappers.responses.SwimmerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Component class that converts a Swimmer instance to a SwimmerResponse instance.
 *
 * Created by anita on 27/12/16.
 */
@Component
public class SwimmerToSwimmerResponseConverter {

    /**
     * Method that converts a list of type Swimmer to a list of type SwimmerResponse.
     * @param swimmers - the list of type Swimmer to be converted.
     * @return a list of type SwimmerResponse as a result of the conversion.
     */
    public List<SwimmerResponse> swimmerListToSwimmerResponseList(List<Swimmer> swimmers) {
        List<SwimmerResponse> result = new ArrayList<>();
        Iterator<Swimmer> swimmerIterator = swimmers.iterator();
        while (swimmerIterator.hasNext()){
            Swimmer swimmer = swimmerIterator.next();
            SwimmerResponse swimmerResponse = new SwimmerResponse();
            swimmerResponse.setId(swimmer.getId());
            swimmerResponse.setGender(swimmer.getGender());
            swimmerResponse.setCategory(swimmer.getCategory());
            swimmerResponse.setFullName(swimmer.getFullName());
            result.add(swimmerResponse);
        }
        return  result;
    }
}

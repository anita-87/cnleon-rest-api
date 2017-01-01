package com.cnleon.converters;

import com.cnleon.enumerates.CompetitionTypes;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Class to convert strings to CompetitionTypes enumerates.
 *
 * Created by anita on 28/11/16.
 */
@Component
public class CompetitionTypesEnumConverter extends PropertyEditorSupport {

    /**
     * Method that parses a string an set it as a CompetitionTypes enum.
     * @param text - the text to parse to a CompetitionTypes
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        CompetitionTypes competitionType = CompetitionTypes.valueOf(text.toUpperCase());
        setValue(competitionType);
    }
}

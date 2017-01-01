package com.cnleon.converters;

import com.cnleon.enumerates.Category;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
/**
 * Class to convert strings to Category enumerates.
 *
 * Created by anita on 18/10/16.
 */
@Component
public class CategoryEnumConverter extends PropertyEditorSupport{

    /**
     * Method that parses a string an set it as a Category enum.
     * @param text - the text to parse to a category
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String capitalized = text.toUpperCase();
        Category category = Category.valueOf(capitalized);
        setValue(category);
    }
}

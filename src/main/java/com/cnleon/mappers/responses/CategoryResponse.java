package com.cnleon.mappers.responses;

/**
 * Response class to represent a swimmer's category.
 *
 * Created by anita on 16/07/17.
 */
public class CategoryResponse {

    /**
     * Localized string for the category
     */
    private String category;

    /**
     * Default constructor
     */
    public CategoryResponse(){}

    /**
     * Constructor for the CategoryResponse class that accept a string parameter.
     * @param category - the string representing the category, to be assigned.
     */
    public CategoryResponse(String category) {
        this.category = category;
    }

    /**
     * Getter method for the category property.
     * @return the category string
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter method for the category property.
     * @param category - string to be assigned to the category property.
     */
    public void setCategory(String category) {
        this.category = category;
    }

}

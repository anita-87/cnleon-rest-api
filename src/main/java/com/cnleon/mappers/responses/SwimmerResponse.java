package com.cnleon.mappers.responses;

import com.cnleon.enumerates.Category;
import com.cnleon.enumerates.Gender;

/**
 * Response class to represent a swimmer response
 * for some of the SwimmersControllers calls.
 *
 * Created by anita on 27/12/16.
 */
public class SwimmerResponse {

    /**
     * Id of the swimmer in the DB.
     */
    String id;
    /**
     * Full name of the swimmer.
     */
    String fullName;

    /**
     * Category of the swimmer.
     */
    Category category;

    /**
     * Gender of the swimmer.
     */
    Gender gender;

    /**
     * Getter for the "id" property.
     * @return the identifier of the swimmer in the SwimmerResponse.
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the "id" property.
     * @param id - the identifier of the swimmer to be assigned to the SwimmerResponse.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for "fullName" property.
     * @return the full name of the swimmer in the SwimmerResponse.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for the "fullName" property.
     * @param fullName - the full name of the swimmer to be assigned to the SwimmerResponse.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Getter for "category" property.
     * @return the category of the swimmer in the SwimmerResponse.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Setter for the "category" property.
     * @param category - the category of the swimmer to be assigned to the SwimmerResponse.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Getter for "gender" property.
     * @return the gender of the swimmer in the SwimmerResponse.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Setter for the "gender" property.
     * @param gender - the gender of the swimmer to be assigned to the SwimmerResponse.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }


}

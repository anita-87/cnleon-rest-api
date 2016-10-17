package com.cnleon.enumerates;

/**
 * Enumerated for the Gender of the swimmers.
 * Possible values are:
 *  <li>MALE</li>
 *  <li>FEMALE</li>
 *
 * Created by anita on 16/10/16.
 */
public enum Gender {
    MALE("M"), FEMALE("F");

    /**
     * Internal value of the gender.
     */
    private String gender;

    /**
     * Public constructor for the enum
     * @param gender - the value of the gender to assign.
     */
    Gender(String gender){
        this.gender = gender;
    }

    /**
     * Setter for the gender
     * @param gender - the gender value to be assigned.
     */
    public void setGender(String gender){
        this.gender = gender;
    }

    /**
     * Getter for the gender
     * @return the value of the gender enumerated.
     */
    public String getGender(){
        return this.gender;
    }
}

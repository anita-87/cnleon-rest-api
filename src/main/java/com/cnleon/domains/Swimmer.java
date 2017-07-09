package com.cnleon.domains;

import com.cnleon.enumerates.Category;
import com.cnleon.enumerates.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Domain class representing a swimmer entity.
 *
 * Created by anita on 12/10/16.
 */

@Document
public class Swimmer {

    /**
     * Id of the swimmer in the DB.
     */
    @Id
    private String _id;
    /**
     * Licence of the swimmer.
     */
    private String licence;
    /**
     * First name of the swimmer.
     */
    private String firstName;
    /**
     * Last name of the swimmer.
     */
    private String lastName;
    /**
     * Gender of the swimmer.
     * @see com.cnleon.enumerates.Gender
     */
    private Gender gender;
    /**
     * Birth date of the swimmer.
     * When represented in JSON format will follow the pattern "dd-MM-yyyy"
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    /**
     * Birth place of the swimmer.
     * Can be just the city or city plus country.
     */
    private String birthPlace;
    /**
     * Boolean property to represent if the swimmer is master or not.
     * This is use because age only is not enough to figure out the category of the swimmer.
     * This property is not shown in the REST API responses.
     */
    private boolean isMaster;
    /**
     * Category of the swimmer. Is not stored in the DB.
     * @see com.cnleon.enumerates.Category
     */
    @Transient
    private Category category;

    /**
     * Getter for the "id" property.
     * @return the identifier of the swimmer.
     */
    public String getId() {
        return _id;
    }

    /**
     * Setter for the "id" property.
     * @param id - the identifier of the swimmer to be assigned.
     */
    public void setId(String id) {
        this._id = id;
    }

    /**
     * Getter for the "licence" property.
     * @return the licence of the swimmer.
     */
    public String getLicence() {
        return licence;
    }

    /**
     * Setter for the "id" property.
     * @param licence - the licence of the swimmer to be assigned.
     */
    public void setLicence(String licence) {
        this.licence = licence;
    }

    /**
     * Getter for the "firstName" property.
     * @return the first name of the swimmer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the "firstName" property.
     * @param firstName - the first name of the swimmer to be assigned.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the "lastName" property.
     * @return the last name of the swimmer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the "lastName" property.
     * @param lastName - the first name of the swimmer to be assigned.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter for the "gender" property.
     * @param gender - the gender of the swimmer to be assigned.
     */
    public void setGender(Gender gender){
        this.gender = gender;
    }

    /**
     * Getter for the "gender" property.
     * @return the gender of the swimmer.
     */
    public Gender getGender(){
        return this.gender;
    }

    /**
     * Getter for the "birthDate" property.
     * @return the birth date of the swimmer.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Setter for the "birthDate" property.
     * @param birthDate - the birth date of the swimmer to be assigned.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter for the "birthPlace" property.
     * @return the birth place of the swimmer.
     */
    public String getBirthPlace() {
        return birthPlace;
    }

    /**
     * Setter for the "birthPlace" property.
     * @param birthPlace - the birth place of the swimmer to be assigned.
     */
    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    /**
     * Getter for the "category" property.
     * @return the category of the swimmer.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Setter for the "category" property.
     * @param category - the category of the swimmer to be assigned.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Getter for the "isMaster" property.
     * @return true if the swimmer is master false if not.
     */
    public boolean getIsMaster(){
        return isMaster;
    }

    /**
     * Setter for the "isMaster" property.
     * @param isMaster - the boolean value to be assigned.
     */
    public void setIsMaster(boolean isMaster){
        this.isMaster = isMaster;
    }

    /**
     * Getter that returns the full name of the swimmer.
     * The full name consists of the first name, a blank space and the last name.
     * @return string with the full name of the swimmer.
     */
    public String getFullName(){ return this.getFirstName() + " " + this.getLastName();}
}

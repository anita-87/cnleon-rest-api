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
 * Created by anita on 12/10/16.
 */

@Document
public class Swimmer {

    @Id
    private String id;
    private String licence;
    private String firstName;
    private String lastName;
    private Gender gender;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDate;
    private String birthPlace;
    @JsonIgnore
    private boolean isMaster;
    @Transient
    private Category category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(Gender gender){
        this.gender = gender;
    }

    public Gender getGender(){
        return this.gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean getIsMaster(){
        return isMaster;
    }

    public void setIsMaster(boolean isMaster){
        this.isMaster = isMaster;
    }
}

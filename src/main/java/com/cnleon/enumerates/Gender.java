package com.cnleon.enumerates;

/**
 * Created by anita on 16/10/16.
 */
public enum Gender {
    MALE("M"), FEMALE("F");

    private String gender;

    Gender(String gender){
        this.gender = gender;
    }

    private void setGender(String gender){
        this.gender = gender;
    }

    private String getGender(){
        return this.gender;
    }
}

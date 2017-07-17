package com.cnleon.enumerates;

/**
 * Enumerated for the categories available for a swimmer.
 *
 * Created by anita on 16/10/16.
 */
public enum Category {
    MASTER, ABSOLUTO, ABSJOVEN, JUNIOR, INFANTIL, ALEVIN, BENJAMIN, PREBENJAMIN;

    /**
     * Returns the category as a localized string in Spanish.
     * @return the spanish localized string.
     */
    @Override
    public String toString() {
        String result = "";
        switch (this) {
            case MASTER:
                result = "Master";
                break;
            case ABSOLUTO:
                result = "Absoluto";
                break;
            case ABSJOVEN:
                result = "Absoluto Joven";
                break;
            case JUNIOR:
                result = "Junior";
                break;
            case INFANTIL:
                result = "Infantil";
                break;
            case ALEVIN:
                result = "Alevin";
                break;
            case BENJAMIN:
                result = "Benjamin";
                break;
            case PREBENJAMIN:
                result = "Prebenjamin";
                break;
            default:
                result = "";
        }

        return result;

    }
}

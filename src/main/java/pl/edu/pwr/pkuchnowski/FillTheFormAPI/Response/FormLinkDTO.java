package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Response;

import jdk.jfr.DataAmount;

/**
 * This class represents a Data Transfer Object (DTO) for a form link.
 * It is used to encapsulate the form link as an object to be easily transferred over the network or between layers
 * in the application.
 */
public class FormLinkDTO {
    String link;
    /**
     * Constructor for the FormLinkDTO class.
     * @param link The link to the form.
     */
    public FormLinkDTO(String link) {
        this.link = link;
    }
    /**
     * Getter for the form link.
     * @return The link to the form.
     */
    public String getLink() {
        return link;
    }
}

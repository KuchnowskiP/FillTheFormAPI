package pl.edu.pwr.pkuchnowski.filltheformapi.response;

/**
 * This class represents a Data Transfer Object (DTO) for a form link.
 * It is used to encapsulate the form link as an object to be easily transferred over the network or between layers
 * in the application.
 *
 * @author Piotr Kuchnowski
 */
public class FormLinkDTO {
    /**
     * The link to the form.
     */
    String link;
    /**
     * Constructor for the FormLinkDTO class.
     * @param link The link to the form.
     */
    public FormLinkDTO(String link) {
        this.link = link;
    }
    /**
     * Getter for the link field.
     * @return The link to the form.
     * It is crucial to have this getter method in order to http response body to be serialized correctly.
     */
    public String getLink() {
        return link;
    }
}

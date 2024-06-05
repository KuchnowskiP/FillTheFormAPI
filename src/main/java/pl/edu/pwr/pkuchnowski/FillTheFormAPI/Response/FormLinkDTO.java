package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Response;

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
     * Getter for the form link.
     * @return The link to the form.
     */
    public String getLink() {
        return link;
    }
}
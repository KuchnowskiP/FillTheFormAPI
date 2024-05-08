package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Services;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * This interface represents a contract for a service that interacts with a forms service.
 * The implementing class should use the provided parameters to generate a form link.
 */

public interface FormService {

    /**
     * This is a contract for a method that, when implemented, is intended to interact with the selected forms service.
     * The implementing class should use the provided parameters to generate a form link.
     * The specifics of how this interaction occurs, and what the form link represents, are up to the implementing class.
     *
     * @param accessToken The access token.
     * @param formId The ID of the form.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param emailAddress The email of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param orderNumber The number of the order.
     * @param orderDate The date of the order.
     * @param orderElements The products of the order.
     * @param quantities The quantities of the products.
     * @return A string that, in the implementing class, should represent a form link.
     * @throws GeneralSecurityException If a security exception occurs during the operation.
     * @throws IOException If an I/O error occurs during the operation.
     */
    String getFormLink(String accessToken, String formId, String firstName, String lastName,
                       String emailAddress, String phoneNumber, String orderNumber, String orderDate,
                       String[] orderElements, String[] quantities) throws GeneralSecurityException, IOException;
}

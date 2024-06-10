package pl.edu.pwr.pkuchnowski.filltheformapi.formgetter;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * This interface is a contract for a method that, when implemented, is intended to interact with the selected forms service.
 * @param <T> The type of the form service response.
 */
public interface FormGetter<T> {
    /**
     * This is a contract for a method that, when implemented, is intended to interact with the selected forms service.
     * The implementing class should use the provided parameters to generate a form and return it as a given type.
     * The specifics of how this interaction occurs, and what the form represents, are up to the implementing class.
     *
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param emailAddress The email of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param orderNumber The number of the order.
     * @param orderDate The date of the order.
     * @param orderElements The products of the order.
     * @param quantities The quantities of the products.
     * @return T The form service response.
     * @throws GeneralSecurityException if a security error occurs
     * @throws IOException if an I/O error occurs
     */
    T getFormResponse(String firstName, String lastName,
                                      String emailAddress, String phoneNumber, String orderNumber, String orderDate,
                                      String[] orderElements, String[] quantities) throws GeneralSecurityException, IOException;
}

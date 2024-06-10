package pl.edu.pwr.pkuchnowski.filltheformapi.service;

import com.google.api.services.script.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.Authorizer;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.ScriptServiceProvider;
import pl.edu.pwr.pkuchnowski.filltheformapi.formgetter.FormGetter;
import pl.edu.pwr.pkuchnowski.filltheformapi.formgetter.GoogleFormGetter;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Service class for getting Google Form responses.
 * Uses the GoogleFormGetter class to get the form response.
 */

@Service
public class FormService {
    private final AuthorizationService authorizationService;

    /**
     * Constructor for the FormService class.
     * {@literal @}Autowired annotation is used for automatic dependency injection.
     * @param authorizationService The service class for creating authorizers and script service providers.
     */

    @Autowired
    public FormService(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    /**
     * Gets the Google Form response.
     * @param formId The ID of the Google Form.
     * @param accessToken The access token obtained from Google OAuth2.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param email The email address of the customer.
     * @param phone The phone number of the customer.
     * @param orderNumber The order number.
     * @param orderDate The order date.
     * @param orderElements The elements of the order.
     * @param elementQuantities The quantities of the elements.
     * @return The Google Form response.
     * @throws GeneralSecurityException If a security error occurs.
     * @throws IOException If an I/O error occurs.
     */
    public Operation getGoogleFormResponse(String formId, String accessToken, String firstName, String lastName,
                                           String email, String phone, String orderNumber, String orderDate,
                                           String[] orderElements, String[] elementQuantities)
            throws GeneralSecurityException, IOException {

        Authorizer authorizer = authorizationService.createGoogleAuthorizer(accessToken);
        ScriptServiceProvider scriptServiceProvider = authorizationService.createScriptServiceProvider(accessToken);

        FormGetter<Operation> googleFormGetter = new GoogleFormGetter(formId, accessToken, authorizer,
                scriptServiceProvider);

        return googleFormGetter.getFormResponse(firstName, lastName, email, phone, orderNumber,
                orderDate, orderElements, elementQuantities);
    }

}

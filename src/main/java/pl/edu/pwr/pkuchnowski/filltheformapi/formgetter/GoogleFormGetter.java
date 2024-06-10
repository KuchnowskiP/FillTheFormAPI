package pl.edu.pwr.pkuchnowski.filltheformapi.formgetter;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.script.Script;
import com.google.api.services.script.model.ExecutionRequest;
import com.google.api.services.script.model.Operation;
import com.google.api.services.script.model.Status;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.Authorizer;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.ScriptServiceProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/**
 * This class interacts with Google Forms to retrieve form links.
 * It uses Google's API Script and OAuth2 for authentication and execution of scripts.
 * This class implements the FormService interface.
 *
 * @see FormGetter <a href="https://developers.google.com/apps-script/api/quickstart/java?hl=pl">Google Apps Script API Java Quickstart</a>
 *
 * @author Piotr Kuchnowski
 */

public class GoogleFormGetter implements FormGetter<Operation> {
    /**
     * ID of the Google Apps Script executable - ID of a deployment of the script.
     */

    private final String APIExecutableId = "AKfycbyKjQKRyukrx-GeoXWbOlYCq_-UuIVX4pTzRr3gXVwhdMwJUaIsTm9_ByS4hnPsj6nU";

    /**
     * The ID of the form.
     */

    private final String formId;

    /**
     * The access token obtained from Google OAuth2.
     */

    private final String accessToken;

    private final ScriptServiceProvider scriptServiceProvider;
    private final Authorizer authorizer;

    /**
     * Creates a GoogleFormGetter object with the provided form ID and access token.
     * @param formId The ID of the form.
     * @param accessToken The access token obtained from Google OAuth2.
     * @param authorizer The authorizer for the Google API.
     * @param scriptServiceProvider The script service provider.
     */

    public GoogleFormGetter(String formId, String accessToken, Authorizer authorizer, ScriptServiceProvider scriptServiceProvider) {
        this.formId = formId;
        this.accessToken = accessToken;
        this.scriptServiceProvider = scriptServiceProvider;
        this.authorizer = authorizer;
    }

    /**
     * Retrieves a form link from Google Forms using the provided parameters.
     * It executes Google Apps Script that generates new form based on template form and pre-fills the new form with customer and order data.
     * This method is an implementation of the getFormLink method from the FormService interface.
     *
     * @param firstName     The first name of the customer.
     * @param lastName      The last name of the customer.
     * @param emailAddress  The email of the customer.
     * @param phoneNumber   The phone number of the customer.
     * @param orderNumber   The number of the order.
     * @param orderDate     The date of the order.
     * @param orderElements The products of the order.
     * @param quantities    The quantities of the products.
     * @return Operation object containing result with a link or an error.
     * @throws GeneralSecurityException If a security exception occurs while creating the script service.
     * @throws IOException              If an I/O error occurs while creating the script service.
     *
     * @see <a href="https://developers.google.com/resources/api-libraries/documentation/script/v1/java/latest/com/google/api/services/script/model/Operation.html">Google Apps Script API Operation</a>
     */

    @Override
    public Operation getFormResponse(String firstName, String lastName,
                                     String emailAddress, String phoneNumber, String orderNumber, String orderDate,
                                     String[] orderElements, String[] quantities)
            throws GeneralSecurityException, IOException {

        authorizer.authorize(); // Create an authorized API client
        Script service = scriptServiceProvider.getScriptService();

        // Execute App Script script with given ID
        ExecutionRequest request = new ExecutionRequest()
                .setFunction("fillForm")
                .setParameters(Arrays.asList(formId ,firstName, lastName, phoneNumber, emailAddress, orderNumber, orderDate, orderElements, quantities));
        try{
            Operation op = service.scripts().run(APIExecutableId, request).execute();
            return op;

        }catch(GoogleJsonResponseException e){
            // The API encountered a problem
            e.printStackTrace(System.out);

            Operation error = new Operation();
            Status errorStatus = new Status();
            errorStatus.setCode(e.getStatusCode());
            String errorDetails = "";
            if(e.getDetails() != null) {
                e.getDetails().getErrors().forEach(errorInfo -> errorDetails.concat(errorInfo.getMessage()));
            }
            errorStatus.setMessage(errorDetails);
            errorStatus.setMessage(e.getStatusMessage());
            error.setError(errorStatus);
            return error;
        }
    }
}

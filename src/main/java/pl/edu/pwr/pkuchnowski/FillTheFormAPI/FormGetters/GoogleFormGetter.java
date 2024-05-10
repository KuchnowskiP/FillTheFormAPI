package pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetters;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.script.Script;
import com.google.api.services.script.model.ExecutionRequest;
import com.google.api.services.script.model.Operation;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.Services.FormService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Map;

/**
 * This class interacts with Google Forms to retrieve form links.
 * It uses Google's API Script and OAuth2 for authentication and execution of scripts.
 * This class implements the FormService interface.
 *
 * @see FormService <a href="https://developers.google.com/apps-script/api/quickstart/java?hl=pl">Google Apps Script API Java Quickstart</a>
 *
 * @author Piotr Kuchnowski
 */

public class GoogleFormGetter implements FormService{
    /**
     * ID of the Google Apps Script executable - ID of the script that will be executed. Obtainable from script settings.
     */
    private static final String APIExecutableId = "AKfycbyKjQKRyukrx-GeoXWbOlYCq_-UuIVX4pTzRr3gXVwhdMwJUaIsTm9_ByS4hnPsj6nU";
    /**
     * The name of the application passed to the Google API.
     */
    private static final String APPLICATION_NAME = "FillTheFormAPI";
    /**
     * The JSON factory used to create JSON objects.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * Creates a Google API Script object using the provided access token.
     * @param accessToken The access token obtained from Google OAuth2.
     * @return A Google API Script object.
     * @throws GeneralSecurityException If a security exception occurs while creating the HTTP transport.
     * @throws IOException If an I/O error occurs while creating the HTTP transport.
     */
    private static Script getScriptService(String accessToken) throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        return new Script.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredentialWithAccessTokenOnly(accessToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    /**
     * Creates a Google API Credential object using the provided access token.
     * @param accessToken The access token obtained from Google OAuth2.
     * @return A Google API Credential object.
     */

    private static Credential createCredentialWithAccessTokenOnly(String accessToken) {
        return new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
    }

    /**
     * Retrieves a form link from Google Forms using the provided parameters.
     * It executes Google Apps Script that generates new form based on template form and pre-fills the new form with customer and order data.
     * This method is an implementation of the getFormLink method from the FormService interface.
     * @param accessToken The access token obtained from Google OAuth2.
     * @param formId The ID of the form.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param emailAddress The email of the customer.
     * @param phoneNumber The phone number of the customer.
     * @param orderNumber The number of the order.
     * @param orderDate The date of the order.
     * @param orderElements The products of the order.
     * @param quantities The quantities of the products.
     * @return The form link if the operation is successful, or an error message if the operation fails.
     * @throws GeneralSecurityException If a security exception occurs while creating the script service.
     * @throws IOException If an I/O error occurs while creating the script service.
     */

    @Override
    public String getFormLink(String accessToken, String formId, String firstName, String lastName,
                                 String emailAddress, String phoneNumber, String orderNumber, String orderDate,
                                 String[] orderElements, String[] quantities)
            throws GeneralSecurityException, IOException {
        
        Script service = getScriptService(accessToken); // Create an authorized API client

        // Execute App Script script with given ID
        ExecutionRequest request = new ExecutionRequest()
                .setFunction("fillForm")
                .setParameters(Arrays.asList(formId ,firstName, lastName, phoneNumber, emailAddress, orderNumber, orderDate, orderElements, quantities));
        try{
            Operation op = service.scripts().run(APIExecutableId, request).execute();
            if (op.getError() != null) {
                // The API executed, but the script returned an error.

                // Extract the first (and only) set of error details. The values of this
                // object are the script's 'errorMessage' and 'errorType', and an array of
                // stack trace elements.
                Map<String, Object> errorDetails = (Map<String, Object>) op.getError().getDetails().get(0);
                String errorMessage = (String) errorDetails.get("errorMessage");
                System.out.println("Script error message: " + errorMessage);

                return "Error message: " + errorMessage;
            } else {
                // The result provided by the API needs to be cast into the correct type,
                // based upon what types the Apps Script function returns. Here, the function
                // returns an Apps Script Object with String keys and values, so must be cast
                // into a Java Map (folderSet).
                Object result = op.getResponse().get("result");
                System.out.println(result.toString());
                return result.toString();
            }
        }catch(GoogleJsonResponseException e){
            // The API encountered a problem before the script was called.
            e.printStackTrace(System.out);
            return e.getMessage();
        }
    }
}

package pl.edu.pwr.pkuchnowski.FillTheFormAPI;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.script.Script;
import com.google.api.services.script.model.ExecutionRequest;
import com.google.api.services.script.model.Operation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FormGetter {
    private static String APIExecutableId = "AKfycbyKjQKRyukrx-GeoXWbOlYCq_-UuIVX4pTzRr3gXVwhdMwJUaIsTm9_ByS4hnPsj6nU";
    private static String formId = "1Yy1k7on8E45bXYqbeZaD-Ki_XJKn1Za91vPdPc-KlJQ";
    private static String firstName = "Jan";
    private static String lastName = "Kowalski";
    private static String emailAddress = "jan.kowalski@example.com";
    private static String phoneNumber = "645123532";
    private static String orderNumber = "2312312";
    private static String orderDate = "January 21, 2023";
    private static String[] orderElements = {"marchewka", "pietruszka"};
    private static String[] quantities = {"5", "1"};
    private static final String APPLICATION_NAME = "Apps Script API Java Quickstart";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials folder at /secret.
     */
    private static final List<String> SCOPES =
            Collections.singletonList("https://www.googleapis.com/auth/script.projects https://www.googleapis.com/auth/forms https://www.googleapis.com/auth/drive https://www.googleapis.com/auth/script.deployments");
    private static final String CREDENTIALS_FILE_PATH = "credentials.json";

    /**
     * Creates an authorized Credential object.
     *
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = FormGetter.class.getClassLoader().getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    public static String getLink(String firstName, String lastName, String emailAddress, String phoneNumber, String orderNumber, String orderDate, String[] orderElements, String[] quantities) throws GeneralSecurityException, IOException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Script service =
                new Script.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

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
                return "Script error message: " + errorMessage;
            } else {
                // The result provided by the API needs to be cast into the correct type,
                // based upon what types the Apps Script function returns. Here, the function
                // returns an Apps Script Object with String keys and values, so must be cast
                // into a Java Map (folderSet).
                Object result = op.getResponse().get("result");
                System.out.println(result.toString());
                return result.toString();
            }
        }catch(GoogleJsonResponseException e) {
            // The API encountered a problem before the script was called.
            e.printStackTrace(System.out);
            return e.getMessage();
        }
    }
}

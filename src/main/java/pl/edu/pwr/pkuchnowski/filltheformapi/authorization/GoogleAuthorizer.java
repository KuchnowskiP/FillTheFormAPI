package pl.edu.pwr.pkuchnowski.filltheformapi.authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.script.Script;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * Authorizes the user to use the Google API Script service.
 * Implements the Authorizer and ScriptServiceProvider interfaces.
 * The Google API Script service is used to execute Google Apps Script functions.
 * The access token is obtained from Google OAuth2.
 */

public class GoogleAuthorizer implements Authorizer, ScriptServiceProvider {

    private static final String APPLICATION_NAME = "FillTheFormAPI";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    /**
     * The Google API Script service.
     */
    private static Script scriptService;

    private final String accessToken;

    /**
     * Constructor for the GoogleAuthorizer class.
     * @param accessToken The access token obtained from Google OAuth2.
     */

    public GoogleAuthorizer(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Returns the Google API Script service.
     * @return The Google API Script service.
     */

    @Override
    public Script getScriptService() {
        return scriptService;
    }

    /**
     * Creates a Google API Credential object using the provided access token.
     * @param accessToken The access token obtained from Google OAuth2.
     * @return A Google API Credential object.
     */

    public Credential createCredential(String accessToken) {
        return new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
    }

    /**
     * Authorizes the Google API Script service using the provided access token.
     */
    @Override
    public void authorize() {
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        scriptService = new Script.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredential(accessToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}

package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Authorization;

import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.script.Script;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleAuthorizer extends Authorizer implements IGoogleAuthorizer {

    private static final String APPLICATION_NAME = "FillTheFormAPI";

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private Script scriptService;

    private final String accessToken;

    @Override
    public Script getScriptService() {
        return scriptService;
    }

    public GoogleAuthorizer(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Creates a Google API Credential object using the provided access token.
     * @param accessToken The access token obtained from Google OAuth2.
     * @return A Google API Credential object.
     */

    @Override
    public Credential createCredential(String accessToken) {
        return new Credential(BearerToken.authorizationHeaderAccessMethod()).setAccessToken(accessToken);
    }

    @Override
    public void authorize() {
        final NetHttpTransport HTTP_TRANSPORT;
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }
        this.scriptService = new Script.Builder(HTTP_TRANSPORT, JSON_FACTORY, createCredential(accessToken))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}

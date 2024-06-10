package pl.edu.pwr.pkuchnowski.filltheformapi.service;

import org.springframework.stereotype.Service;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.Authorizer;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.GoogleAuthorizer;
import pl.edu.pwr.pkuchnowski.filltheformapi.authorization.ScriptServiceProvider;

/**
 * Service class for creating authorizers and script service providers.
 */

@Service
public class AuthorizationService {
    /**
     * Creates a Google authorizer.
     *
     * @param accessToken Access token for the authorizer.
     * @return Google authorizer.
     */
    public Authorizer createGoogleAuthorizer(String accessToken) {
        return new GoogleAuthorizer(accessToken);
    }

    /**
     * Creates a script service provider.
     *
     * @param accessToken Access token for the script service provider.
     * @return Script service provider.
     */
    public ScriptServiceProvider createScriptServiceProvider(String accessToken) {
        return new GoogleAuthorizer(accessToken);
    }
}

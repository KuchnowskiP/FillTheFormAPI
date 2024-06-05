package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Authorization;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.script.Script;

public interface IGoogleAuthorizer {
    void authorize();
    Script getScriptService();
    Credential createCredential(String accessToken);
}

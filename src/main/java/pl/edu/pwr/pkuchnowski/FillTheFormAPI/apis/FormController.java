package pl.edu.pwr.pkuchnowski.FillTheFormAPI.apis;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.CredentialDTO;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormLinkDTO;

@RestController
public class FormController {

    @RequestMapping(value = "/getFormLink", method = RequestMethod.GET)
    public ResponseEntity<FormLinkDTO> getFormLink(@RequestHeader(value="Authorization") String authorizationHeader,
                                                   @RequestParam String formId, @RequestParam String firstName,
                                                   @RequestParam String lastName, @RequestParam String email,
                                                   @RequestParam String phone, @RequestParam String orderNumber,
                                                   @RequestParam String orderDate,
                                                   @RequestParam("element") String[] orderElements,
                                                   @RequestParam("quantity") String[] elementQuantities)
            throws GeneralSecurityException, IOException, JSONException {
        String accessToken = authorizationHeader.split(" ")[1]; //acess token extraction
        String result = FormGetter.getLink(accessToken, formId, firstName, lastName, email, phone, orderNumber,
                orderDate, orderElements, elementQuantities); //getting a form link
        if(result.contains("401")){ //return 401 if access token expired
            return new ResponseEntity<>(new FormLinkDTO(result), HttpStatus.UNAUTHORIZED);
        }
        FormLinkDTO formLinkDTO = new FormLinkDTO(result);
        if(formLinkDTO.getLink().contains("error")) {
            return ResponseEntity.badRequest().body(formLinkDTO);
        }
        return ResponseEntity.ok().body(formLinkDTO);
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Hello");
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    public ResponseEntity<CredentialDTO> me(@RequestHeader(value="Authorization") String authorizationHeader) throws GeneralSecurityException, IOException {
        String accessToken = authorizationHeader.split(" ")[1];
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Credential credential = FormGetter.createCredentialWithAccessTokenOnly(HTTP_TRANSPORT, accessToken);
        if(credential == null) {
            return ResponseEntity.badRequest().build();
        }
        CredentialDTO credentialDTO = new CredentialDTO(credential.getAccessToken(), credential.getRefreshToken());
        return ResponseEntity.ok().body(credentialDTO);
    }

}

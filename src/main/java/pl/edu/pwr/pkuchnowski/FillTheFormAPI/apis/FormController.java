package pl.edu.pwr.pkuchnowski.FillTheFormAPI.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
public class FormController {

    @RequestMapping(value = "/getFormLink", method = RequestMethod.GET)
    public String getFormLink(@RequestHeader(value="Authorization") String authorizationHeader, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                              @RequestParam String phone, @RequestParam String orderNumber,
                              @RequestParam String orderDate, @RequestParam("element") String[] orderElements
            , @RequestParam("quantity") String[] elementQuantities) throws GeneralSecurityException, IOException {
            String accessToken = authorizationHeader.split(" ")[1];
        return FormGetter.getLink(accessToken, firstName, lastName, email, phone, orderNumber, orderDate, orderElements
                , elementQuantities);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

}

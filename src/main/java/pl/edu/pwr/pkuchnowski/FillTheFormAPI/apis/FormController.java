package pl.edu.pwr.pkuchnowski.FillTheFormAPI.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetter;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class FormController {
    @GetMapping("/getFormLink")
    public String getFormLink(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email,
                              @RequestParam String phone, @RequestParam String orderNumber,
                              @RequestParam String orderDate, @RequestParam("element") String[] orderElements
            , @RequestParam("quantity") String[] elementQuantities) throws GeneralSecurityException, IOException {
        return FormGetter.getLink(firstName, lastName, email, phone, orderNumber, orderDate, orderElements
                , elementQuantities);
    }
}

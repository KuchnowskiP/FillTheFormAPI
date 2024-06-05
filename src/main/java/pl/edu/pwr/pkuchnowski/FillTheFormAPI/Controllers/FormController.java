package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;


import com.google.api.services.script.model.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetters.GoogleFormGetter;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.Response.FormLinkDTO;
import pl.edu.pwr.pkuchnowski.FillTheFormAPI.FormGetters.FormGetter;

/**
 * Controller class for handling requests from the client.
 * This class handles requests to retrieve form links from Google Forms and to test the server status.
 * It interacts with the FormGetter class to retrieve form links and constructs appropriate responses based on the results.
 *
 * @author Piotr Kuchnowski
 */

@RestController
public class FormController {
    /**
     * Handles a request to get a form link from Google Forms.
     * This method takes template form id, customer data and order data as parameters, as well as an access token from the header of the request.
     * It uses the FormGetter class to retrieve the form link and constructs a response based on the result.
     * If the access token is expired, it returns a 401 status code.
     * If an error occurs while retrieving the form link, it returns a 400 status code.
     * Otherwise, it returns the form link with a 200 status code.
     * @param authorizationHeader The access token obtained from Google OAuth2.
     * @param formId The ID of the form.
     * @param firstName The first name of the customer.
     * @param lastName The last name of the customer.
     * @param email The email of the customer.
     * @param phone The phone number of the customer.
     * @param orderNumber The number of the order.
     * @param orderDate The date of the order.
     * @param orderElements The products of the order.
     * @param elementQuantities The quantities of the products.
     * @return A Response Entity containing the Google Forms form link or an error code and message.
     * @throws GeneralSecurityException If a security exception occurs while retrieving the form link.
     * @throws IOException If an I/O error occurs while retrieving the form link.
     *
     * @see <a href="https://developers.google.com/resources/api-libraries/documentation/script/v1/java/latest/com/google/api/services/script/model/Operation.html">Google Apps Script API Operation</a>
     */
    @RequestMapping(value = "/getFormLink", method = RequestMethod.GET)
    public ResponseEntity<FormLinkDTO> getFormLink(@RequestHeader(value="Authorization") String authorizationHeader,
                                                   @RequestParam String formId, @RequestParam String firstName,
                                                   @RequestParam String lastName, @RequestParam String email,
                                                   @RequestParam String phone, @RequestParam String orderNumber,
                                                   @RequestParam String orderDate,
                                                   @RequestParam("element") String[] orderElements,
                                                   @RequestParam("quantity") String[] elementQuantities)
            throws GeneralSecurityException, IOException {
        String accessToken = authorizationHeader.split(" ")[1]; //acess token extraction
        FormGetter<Operation> googleFormGetter = new GoogleFormGetter(formId, accessToken); //creating GoogleFormGetter object
        Operation result = googleFormGetter.getFormResponse(firstName, lastName, email, phone, orderNumber,
                orderDate, orderElements, elementQuantities); //getting Apps Script operation result
        if(result.getError() != null){ //if error occurred
            for(HttpStatus status : HttpStatus.values()){ //check if error code is in the list of HTTP status codes
                if(status.value() == result.getError().getCode()){ //if it is
                    return ResponseEntity.status(status).body(new FormLinkDTO(result.getError().getCode() + " "
                            + result.getError().getMessage())); //return error code and message
                }
            }
        }
        return ResponseEntity.ok().body(new FormLinkDTO(result.getResponse().get("result").toString())); //return form link
    }
}

package pl.edu.pwr.pkuchnowski.filltheformapi.controller;


import com.google.api.services.script.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.pwr.pkuchnowski.filltheformapi.response.FormLinkDTO;
import pl.edu.pwr.pkuchnowski.filltheformapi.service.FormService;

/**
 * Controller class for handling requests related to forms.
 * This class is responsible for handling all form-related requests from the client.
 * It currently supports operations for creating a new form on Google Forms and retrieving the link to the created form.
 * More endpoints can be added in the future to support additional form-related operations.
 *
 * <p>The controller is annotated with `@RestController` which automatically add the `@ResponseBody` annotation to all the handler methods.
 * It is also annotated with @RequestMapping to specify the base URL path for all endpoints in this class.</p>
 *
 * @author Piotr Kuchnowski
 */

@RestController
@RequestMapping("/api/forms")
public class FormController {
    private final FormService formService;

    /**
     * Constructor for the FormController class.
     * {@literal @}Autowired annotation is used for automatic dependency injection.
     * @param formService The service class for handling form-related operations.
     */

    @Autowired
    public FormController(FormService formService) {
        this.formService = formService;
    }

    /**
     * Handles a request to get a form link from Google Forms.
     * This method takes template form id, customer data and order data as parameters, as well as an access token from the header of the request.
     * It uses the FormGetter class to retrieve the form link and constructs a response based on the result.
     * If the access token is expired, it returns a 401 status code.
     * If an error occurs while retrieving the form link, it returns a 400 status code.
     * Otherwise, it returns the form link with a 200 status code.
     * <p>This method is annotated with @RequestMapping to specify the URL path and HTTP method for this endpoint.</p>
     *
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
    @RequestMapping(value = "/google-form-link", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<FormLinkDTO> getFormLink(@RequestHeader(value="Authorization") String authorizationHeader,
                                                   @RequestParam String formId, @RequestParam String firstName,
                                                   @RequestParam String lastName, @RequestParam String email,
                                                   @RequestParam String phone, @RequestParam String orderNumber,
                                                   @RequestParam String orderDate,
                                                   @RequestParam("element") String[] orderElements,
                                                   @RequestParam("quantity") String[] elementQuantities)
            throws GeneralSecurityException, IOException {

        String accessToken = authorizationHeader.split(" ")[1];

        Operation result = formService.getGoogleFormResponse(formId, accessToken, firstName, lastName, email, phone,
                orderNumber, orderDate, orderElements, elementQuantities);


        if(result.getError() != null){ //if error occurred
            for(HttpStatus status : HttpStatus.values()){
                if(status.value() == result.getError().getCode()){ //if it is
                    return ResponseEntity.status(status).body(new FormLinkDTO(result.getError().getCode() + " "
                            + result.getError().getMessage())); //return error code and message
                }
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new FormLinkDTO("Unknown error occurred"));
        }
        return ResponseEntity.ok().body(new FormLinkDTO(result.getResponse().get("result").toString())); //return form link
    }
}

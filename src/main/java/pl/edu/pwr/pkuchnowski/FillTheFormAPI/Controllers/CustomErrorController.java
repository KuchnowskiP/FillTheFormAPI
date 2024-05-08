package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Custom error controller to handle errors and redirect to a 404 page.
 *
 * <p>This class implements the ErrorController which is a marker interface used to identify error controllers.
 * When an error occurs, the `handleError` method is invoked, which redirects the user to a 404 page.
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/error/ErrorController.html">ErrorController</a>
 *
 * @author Piotr Kuchnowski
 */
@RestController
public class CustomErrorController implements ErrorController {

    /**
     * Handles errors by redirecting to a 404 page.
     * <p>This method is invoked when an error occurs and redirects the user to a 404 page.
     * @return a RedirectView that redirects to the 404 page.
     */
    @RequestMapping("/error")
    public RedirectView handleError() {
        return new RedirectView("/404.html");
    }
}
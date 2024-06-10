package pl.edu.pwr.pkuchnowski.filltheformapi.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Custom error controller to handle errors and redirect to a 404 page.
 *
 * <p>This class implements the ErrorController which is a marker interface used to identify error controllers.
 * When an error occurs, the `handleError` method is invoked, which redirects the user to a 404 page.
 * The controller is annotated with `@RestController` which automatically add the `@ResponseBody` annotation to all the handler methods.</p>
 *
 * @see <a href="https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/servlet/error/ErrorController.html">ErrorController</a>
 *
 * @author Piotr Kuchnowski
 */
@RestController
public class CustomErrorController implements ErrorController {

    /**
     * Handles errors by redirecting to a 404 page.
     * <p>This method is invoked when an error occurs and redirects the user to a 404 page.</p>
     * @return a ResponseEntity with a 404 status code and the content of the 404.html file
     * @throws IOException if an I/O error occurs
     */
    @GetMapping("/error")
    public ResponseEntity<String> handleError() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/404.html");
        return ResponseEntity.status(404).body(new String(resource.getInputStream().readAllBytes()));
    }
}
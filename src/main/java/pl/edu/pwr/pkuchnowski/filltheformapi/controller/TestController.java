package pl.edu.pwr.pkuchnowski.filltheformapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to handle requests to test the server status.
 * This controller contains a single method to handle a request to test the server status.
 * The method returns a simple "Hello" message with a 200 status code.
 * The controller is annotated with `@RestController` which automatically add the `@ResponseBody` annotation to all the handler methods.
 */
@RestController
public class TestController {
    /**
     * Handles a request to test the server status.
     * This method returns a simple "Hello" message with a 200 status code.
     * @return A Response Entity containing the "Hello" message.
     */
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok().body("Hello");
    }
}

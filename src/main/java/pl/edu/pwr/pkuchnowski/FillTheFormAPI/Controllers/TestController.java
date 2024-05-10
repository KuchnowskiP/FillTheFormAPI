package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test controller to handle requests to test the server status.
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

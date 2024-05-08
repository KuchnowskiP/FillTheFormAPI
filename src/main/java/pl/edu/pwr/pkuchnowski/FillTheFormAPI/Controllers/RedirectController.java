package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Redirect controller to handle redirects to the Javadoc.
 *
 * <p>This class implements a controller that redirects the user to the Javadoc when the user accesses the `/javadoc` endpoint.
 *
 * @author Piotr Kuchnowski
 */
@RestController
public class RedirectController {
    /**
     * Redirects the user to the Javadoc.
     *
     * <p>This method is invoked when the user accesses the `/javadoc` endpoint.
     * It redirects the user to the Javadoc by sending a redirect response to the client.
     *
     * @param response the HttpServletResponse to send the redirect response to.
     * @throws IOException if an I/O error occurs while sending the redirect response.
     */
    @RequestMapping("/javadoc")
    void handleJavadoc(HttpServletResponse response) throws IOException {
        response.sendRedirect("/javadoc/index.html");
    }
}

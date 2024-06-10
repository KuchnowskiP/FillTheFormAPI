package pl.edu.pwr.pkuchnowski.filltheformapi.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Redirect controller to handle redirects to the Javadoc and Swagger UI.
 *
 * <p>This class implements a controller that redirects the user to the Javadoc when the user accesses the `/javadoc` endpoint and
 * to the Swagger UI when the user accesses the `/swagger-ui` endpoint. It sends a redirect response to the client to redirect the user to the appropriate page.
 * The controller is annotated with `@RestController` which automatically add the `@ResponseBody` annotation to all the handler methods.</p>
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
    @GetMapping("/javadoc")
    void handleJavadoc(HttpServletResponse response) throws IOException {
        response.sendRedirect("/javadoc/index.html");
    }
    /**
     * Redirects the user to the Javadoc.
     *
     * <p>This method is invoked when the user accesses the `/javadoc/` endpoint.
     * It redirects the user to the Javadoc by sending a redirect response to the client.
     *
     * @param response the HttpServletResponse to send the redirect response to.
     * @throws IOException if an I/O error occurs while sending the redirect response.
     */
    @GetMapping("/javadoc/")
    void handleJavadocSlash(HttpServletResponse response) throws IOException {
        response.sendRedirect("/javadoc/index.html");
    }
    /**
     * Redirects the user to the Swagger UI.
     *
     * <p>This method is invoked when the user accesses the `/swagger-ui` endpoint.
     * It redirects the user to the Swagger UI by sending a redirect response to the client.
     *
     * @param response the HttpServletResponse to send the redirect response to.
     * @throws IOException if an I/O error occurs while sending the redirect response.
     */
    @GetMapping("/swagger-ui")
    void handleSwaggerUI(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }
    /**
     * Redirects the user to the Swagger UI.
     *
     * <p>This method is invoked when the user accesses the `/swagger-ui/` endpoint.
     * It redirects the user to the Swagger UI by sending a redirect response to the client.
     *
     * @param response the HttpServletResponse to send the redirect response to.
     * @throws IOException if an I/O error occurs while sending the redirect response.
     */
    @GetMapping("/swagger-ui/")
    void handleSwaggerUISlash(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/index.html");
    }
}

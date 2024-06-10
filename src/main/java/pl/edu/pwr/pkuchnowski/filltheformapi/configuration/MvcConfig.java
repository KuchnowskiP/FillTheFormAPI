package pl.edu.pwr.pkuchnowski.filltheformapi.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC configuration class.
 *
 * <p>This class is responsible for configuring the resources of the application.
 * It implements the WebMvcConfigurer interface which provides callback methods to customize the Java-based configuration for Spring MVC.
 *
 * <p>In this class, the addResourceHandlers method is overridden to add a resource handler for serving Javadoc.
 *
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html">WebMvcConfigurer</a>
 *
 * @author Piotr Kuchnowski
 */

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Add resource handlers to serve Javadoc.
     *
     * <p>This method overrides the addResourceHandlers method of the WebMvcConfigurer interface.
     * It adds a resource handler for "/javadoc/**" and sets the resource location to the Javadoc path.
     *
     * @param registry the ResourceHandlerRegistry to add the resource handler to.
     * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/WebMvcConfigurer.html#addResourceHandlers-org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry-">addResourceHandlers</a>
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String javadocPath = System.getProperty("javadoc.path", "./docs/javadoc/");
        registry.addResourceHandler("/javadoc/**")
                .addResourceLocations("file:" + javadocPath);
        System.out.println("Javadoc path: " + javadocPath);
    }
}
package pl.edu.pwr.pkuchnowski.filltheformapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the FillTheFormAPI application.
 * @version 1.0: the application interacts with Google Forms via Google Apps Script. It generates - based on the template form -
 * pre-filled form and retrieve its links.
 * It uses Google's API Script and OAuth2 for authentication and execution of pre-fill script that
 * pre-fills Google Form with provided customer and order data.
 *
 * @author Piotr Kuchnowski
 */
@SpringBootApplication
public class FillTheFormApiApplication {
	/**
	 * The main entry point for the application.
	 * This method launches the Spring Boot application.
	 * @param args Arguments to the application. Not currently used.
	 */
	public static void main(String[] args) {
		SpringApplication.run(FillTheFormApiApplication.class, args);
	}

}

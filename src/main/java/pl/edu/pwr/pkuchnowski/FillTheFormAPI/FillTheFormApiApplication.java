package pl.edu.pwr.pkuchnowski.FillTheFormAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class of the FillTheFormAPI application.
 * This application is designed to interact with Google Forms via Google Apps Script. It generates - based on the template form -
 * forms and retrieve form links.
 * It uses Google's API Script and OAuth2 for authentication and execution of pre-fill script that
 * pre-fills Google Form with provided customer and order data.
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

# FillTheFormAPI

FillTheFormAPI is a Spring Boot application that interacts with Google Forms to generate form links based on a template form and pre-filled customer and order data.

## Features

- Retrieve form links from Google Forms
- Test server status

## Technologies

- Java
- Spring Boot
- Gradle

## Setup

To run this project, you need to:

1. Clone the repository
2. Navigate to the project directory
3. Run the Spring Boot application

On Linux:
git clone https://github.com/KuchnowskiP/FillTheFormAPI.git
cd FillTheFormAPI
sudo ./gradlew bootRun # If permission denied, run 'sudo chmod +x gradlew' before

On Windows:
git clone https://github.com/KuchnowskiP/FillTheFormAPI.git
cd FillTheFormAPI
gradlew :bootRun

## Endpoints

### FormController
Handles operations related to form creation and retrieval.

- POST /api/form/google-form-link: Generate a Google Form link based on the provided template form and customer and order data.

### RedirectController
Handles redirections and testing.

- GET /javadoc: Redirect to the Javadoc documentation.
- GET /javadoc/: Redirect to the Javadoc documentation.
- GET /swagger-ui: Redirect to the Swagger UI documentation.
- GET /swagger-ui/: Redirect to the Swagger UI documentation.

### CustomErrorController
Handles custom error responses.

- GET /error: Return a custom 404 error page with a link to the index page.

### TestController

- GET /hello: Return a simple greeting message.
- 
## Documentation

Documentation is available at https://kuchnowskip.github.io/FillTheFormAPI/.

If you run this application on your local machine, by default, you can access Javadoc at http://localhost:8085/javadoc.
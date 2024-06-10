# FillTheFormAPI

FillTheFormAPI is a Spring Boot application that interacts with Google Forms to generate form links based on template form and pre-filled customer and order data.

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
```bash
git clone https://github.com/KuchnowskiP/FillTheFormAPI.git
cd FillTheFormAPI
sudo ./gradlew bootRun # If permission denied, run 'sudo chmod +x gradlew' before
```
On Windows:
```bash
git clone https://github.com/KuchnowskiP/FillTheFormAPI.git
cd FillTheFormAPI
gradlew :bootRun
```

### Documentation
Documentation is availabale at https://kuchnowskip.github.io/FillTheFormAPI/
If you run this application on your local machine, you can acccess Javadoc at localhost:8085/javadoc 

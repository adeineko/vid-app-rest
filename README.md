# Video Platform Application

An application to manage data about your channels, including videos, comments, and other necessary information.

## Author

**Anna Deineko**

Email: [anna.deineko@student.kdg.be](mailto:anna.deineko@student.kdg.be)

## Table of Contents

- [About](#about)
- [Domain Model](#domain-model)
- [Build and Run Instructions](#build-and-run-instructions)
    - [Prerequisites](#prerequisites)
    - [Starting Docker Containers](#starting-docker-containers)
    - [Building the Application](#building-the-application)
    - [Running the Application](#running-the-application)
    - [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## About

This application allows you to manage data related to your video channels, including videos, comments, and other necessary information.

## Domain Model

- **Videos - Comments**: One-to-Many relationship
- **Videos - Channels**: Many-to-Many relationship

## Build and Run Instructions

### Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Java Development Kit (JDK) 8 or higher](https://www.oracle.com/java/technologies/downloads/)
- [Gradle](https://gradle.org/install/) (or use the provided Gradle wrapper)

### Starting Docker Containers

Start the required Docker containers:

```bash
docker compose --profile "*" up -d
```

### Building the Application

Generate the JAR file using Gradle:

```bash
./gradlew build
```

### Running the Application

Run the application using the generated JAR file:

```bash
java -jar build/libs/prog5_app-0.0.1-SNAPSHOT.jar fully.qualified.package.Application
```

*Note:* Replace `fully.qualified.package.Application` with the fully qualified name of your main application class.

### Running Tests

To run the tests with detailed information, execute:

```bash
./gradlew clean test --info
```

## Contact

For any questions or feedback, feel free to reach out:

- **Anna Deineko**
- Email: [anna.deineko@student.kdg.be](mailto:anna.deineko@student.kdg.be)
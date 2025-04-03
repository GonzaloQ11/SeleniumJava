# Serenity BDD Test Automation Framework

This project is a test automation framework built with **Serenity BDD**, **JUnit**, **Cucumber**, **Selenium WebDriver**, and **Java**.

---

## Tech Stack

- Java
- Maven
- JUnit
- Serenity BDD
- Cucumber
- Selenium WebDriver

---

## Project Structure

```
src
├── main
│   ├── java
│   │   ├── base                 # Base page logic
│   │   ├── config               # Properties configuration reader class
│   │   ├── drivers              # Browser driver manager
│   │   ├── enums                # Enums helper
│   │   ├── pages                # Page objects
│   │   └── pojos                # API POJOs
│   └── resources
│       └── config               # Configuration properties for UI
│
└── test
    ├── java
    │   ├── api
    │   │   ├── runner           # Cucumber runner for API tests
    │   │   └── steps            # Step definitions for API tests
    │   ├── base                 # Base test helper class
    │   └── tests                # UI test classes
    └── resources
        └── features             # Gherkin feature files

```

---

## How to Run the Tests

### Prerequisites

- Java 8 or higher
- Maven installed and configured

### Run All Tests with Maven

```bash
mvn clean verify
```

This will execute all tests and generate a Serenity report.

---

## Reports

### Serenity Report

After test execution, open the HTML report:

```
target/site/serenity/index.html
```
---

## Built With

- [Serenity BDD](https://serenity-bdd.github.io/theserenitybook/latest/)
- [Cucumber](https://cucumber.io/)
- [JUnit 4](https://junit.org/junit4/)
- [Selenium WebDriver](https://www.selenium.dev/)

---

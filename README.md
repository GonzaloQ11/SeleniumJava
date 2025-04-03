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
    │   ├── runner               # Cucumber runner for UI and API tests
    │   └── stepdefinitions      
    │       └── api              # API Steps
    │       └── ui               # UI Steps
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
---

## Reports

### Serenity Report

After test execution, run:
```bash
 mvn serenity:aggregate
```

Then open the HTML report at:

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

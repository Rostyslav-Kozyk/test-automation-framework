# Test Automation Portfolio (UI + API)

This repository showcases my skills as a **Test Automation Engineer**, focusing on clean architecture, maintainable
code, and industry best practices.

The project is split into two Maven modules that can be run independently or together:

* **UI Test Automation** (Selenium)
* **API Test Automation** (REST Assured)

The main goals of this portfolio are to demonstrate:

* Clean and scalable test architecture
* Readable, maintainable automation code
* Realistic UI end-to-end and API test scenarios

---

## 🧰 Tech Stack

### UI Automation

* Java 17
* Selenium WebDriver
* TestNG
* Maven
* Allure Reporting
* WebDriverManager

### API Automation

* Java 17
* REST Assured
* TestNG
* Swagger Schema Validation

---

## 📁 Project Structure

```
├── ui-tests/     # UI test automation (Selenium)
├── api-tests/    # API test automation (REST Assured)
├── pom.xml       # Maven aggregator and shared Allure report
└── README.md
```

---

## 🖥️ UI Test Automation

### Target Application

* [https://www.saucedemo.com/](https://www.saucedemo.com/)

### Implemented Scenarios

* Login (positive and negative cases)
* Add product to cart
* Complete checkout flow with cart, totals, and confirmation validation
* Required checkout information validation

### Architecture Highlights

* Page Object Model (POM)
* Centralized `WebDriverFactory`
* `BaseTest` with setup and teardown logic
* Assertions separated from test flow
* Builder-based checkout test data
* Allure reporting with screenshots on failure

### Run UI Tests

```bash
cd ui-tests
mvn clean test
```

Run tests with browser configuration:

```bash
mvn test -Dbrowser=firefox -Dheadless=true
```

Generate Allure report:

```bash
mvn allure:serve
```

---

## 🔌 API Test Automation

### Target API

* [https://reqres.in/](https://reqres.in/)

### Implemented Scenarios

* Get users list
* Get user by ID
* Create user with request and response DTO validation
* Replace and partially update users with PUT and PATCH
* Delete users and validate empty 204 responses
* Negative test cases (404, invalid user)
* Swagger schema validation

### Architecture Highlights

* Base API client abstraction
* Dedicated API clients (e.g. `UsersClient`)
* DTO-based response models
* Separate assertion layer
* Contract testing using Swagger schema validation

### Run API Tests

```bash
cd api-tests
mvn clean test
```

---

## 📊 Shared UI and API Allure Report

Run both test modules from the repository root. Their results are collected in one directory:

```bash
mvn clean test
```

Generate the combined report:

```bash
mvn -N allure:report
```

The generated report is available in `target/allure-report`.

To generate and open the report in a local browser:

```bash
mvn -N allure:serve
```

The TestNG suite names distinguish `API Tests Suite` and `UI Tests Suite` inside Allure.

---

## 🧠 Design Principles Used

* Separation of concerns (tests, pages/clients, assertions)
* Reusability and scalability
* Centralized dependency and plugin versions in the parent Maven configuration
* Clean, readable, and interview-friendly code
* CI/CD-ready project structure

---

## 🚀 Possible Future Improvements

* Docker and CI integration

---

## 👤 Author

**Rostyslav Kozyk**
Test Automation Engineer
Java | Selenium | REST Assured | TestNG

# Test Automation Portfolio (UI + API)

This repository showcases my skills as a **Test Automation Engineer**, focusing on clean architecture, maintainable code, and industry best practices.

The project is split into two independent Maven modules:

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
└── README.md
```

---

## 🖥️ UI Test Automation

### Target Application

* [https://www.saucedemo.com/](https://www.saucedemo.com/)

### Implemented Scenarios

* Login (positive and negative cases)
* Add product to cart
* Basic end-to-end user flow

### Architecture Highlights

* Page Object Model (POM)
* Centralized `WebDriverFactory`
* `BaseTest` with setup and teardown logic
* Assertions separated from test flow
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

## 🧠 Design Principles Used

* Separation of concerns (tests, pages/clients, assertions)
* Reusability and scalability
* Clean, readable, and interview-friendly code
* CI/CD-ready project structure

---

## 🚀 Possible Future Improvements

* Checkout flow UI tests
* API POST/PUT tests with request DTOs
* Shared Allure reporting for UI and API modules
* Test data builders
* Docker and CI integration

---

## 👤 Author

**Rostyslav Kozyk**
Test Automation Engineer
Java | Selenium | REST Assured | TestNG

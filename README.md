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
* Docker Compose

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
* Allure reporting with screenshots, current URLs, and page sources on UI failures

### Run UI Tests

```bash
cd ui-tests
mvn clean test
```

Run tests with browser configuration:

```bash
mvn test -Dbrowser=firefox -Dheadless=true
```

Run against an existing Selenium Grid instead of a locally installed browser:

```bash
mvn test -Dremote.url=http://localhost:4444/wd/hub -Dbrowser=chrome -Dheadless=true
```

UI test classes run in parallel with three threads by default. Override the thread count when needed:

```bash
mvn test -Dheadless=true -Dui.thread.count=4
```

Select a UI environment or provide a custom base URL and explicit-wait timeout:

```bash
mvn test -Denv=PROD
mvn test -Denv=QA
mvn test -Dui.base.url=https://www.saucedemo.com -Dui.timeout.seconds=15
```

Run a specific UI test group:

```bash
mvn test -Dgroups=smoke
mvn test -Dgroups=regression
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

Select a configured API environment or provide a custom base URL:

```bash
mvn test -Denv=PROD
mvn test -Dapi.base.url=https://reqres.in/api
```

Run a specific API test group:

```bash
mvn test -Dgroups=smoke
mvn test -Dgroups=regression
```

---

## ✅ Code Quality Gate

Run static code-quality checks without executing tests:

```bash
mvn -DskipTests verify
```

Use `mvn verify` for the complete quality gate including all tests. This is the command intended
for the future Jenkins pipeline.

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
The report's Environment section records the selected API and UI targets, browser settings,
Java version, and operating system. Secrets such as `API_KEY` are never included.

### Portfolio Report Sample

A compact, versioned [test execution sample](docs/test-report-sample.md) presents the latest
successful API and UI run without publishing raw logs, HTTP traffic, machine paths, or secrets.
After running the complete suite, refresh it from the Surefire XML results:

```powershell
./scripts/Export-PortfolioReport.ps1
```

The exporter deliberately reads only test names, statuses, counts, and durations. The detailed
Allure report remains a local build artifact and can still be generated with the commands above.

---

## 🐳 Reproducible Docker Execution

The Docker setup runs Maven with Java 17 and executes the parallel UI tests against a dedicated,
headless Selenium Chrome container. API and UI tests still write their shared Allure results to
the host's `target/allure-results` directory.

Set the API key only for the current shell session and start the complete quality gate:

```powershell
$env:API_KEY = "your-api-key"
docker compose up --abort-on-container-exit --exit-code-from tests
docker compose down
```

The key is passed to the test container at runtime. It is not stored in the Compose file, image,
source code, or Allure environment data.

---

## 🧠 Design Principles Used

* Separation of concerns (tests, pages/clients, assertions)
* Reusability and scalability
* Consistent, centralized runtime configuration for API and UI modules
* Centralized dependency and plugin versions in the parent Maven configuration
* TestNG groups for smoke, regression, API, and UI test selection
* Maven quality gate with Checkstyle and SpotBugs
* Clean, readable, and interview-friendly code
* CI/CD-ready project structure

---

## 🚀 Possible Future Improvements

* Jenkins integration

---

## 👤 Author

**Rostyslav Kozyk**
Test Automation Engineer
Java | Selenium | REST Assured | TestNG

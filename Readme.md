# 📚 Book Store API

A sample project demonstrating a **Book Store** RESTful API built with:

- 🧩 **Spring Boot 3.5**
- 📘 **OpenAPI Specification + Code Generation**
- 🗄️ **Spring Data JPA**
- ⚙️ **Maven**
- 🧪 Optional Swagger UI via Springdoc

---

## 🏗️ Features

- 📖 Manage books with attributes: title, ISBN, published flag, author, chapters
- ✍️ Author and Chapter as separate entities
- 🔄 Full CRUD endpoints for books (and optionally nested entities)
- 🔧 OpenAPI YAML spec used to **generate controllers, DTOs, and interfaces**
- 📂 Generated code stored in structured packages under `com.example.bookstore.generated.*`
- 🗃️ H2 in-memory database for development and testing

---

## 📦 Technologies

| Tool/Library           | Usage                         |
|------------------------|-------------------------------|
| Spring Boot 3.5        | Core framework                |
| Spring Data JPA        | Persistence layer             |
| OpenAPI Generator 7.5  | Generate API code from spec   |
| Maven                  | Build and dependency management |
| H2 Database            | In-memory dev DB              |
| Lombok                 | Boilerplate reduction         |
| Springdoc OpenAPI (UI) | Swagger UI for API docs (optional) |

---

## 📁 Project Structure

    api/
    └── api.yaml # OpenAPI 3.0 spec
    src/
    └── main/
    ├── java/
    │ ├── com.example.bookstore
    │ │ ├── domain/ # JPA entities
    │ │ ├── repository/ # Spring Data JPA interfaces
    │ │ ├── service/ # Business logic
    │ │ └── controller/ # Optional custom controllers
    │ └── com.example.bookstore.generated/
    │ ├── api/ # Generated API interfaces
    │ ├── dto/ # Generated DTOs
    │ └── config/ # Supporting OpenAPI config
    └── resources/
    └── application.yml


---

## 🚀 Getting Started

### Prerequisites

- Java 21
- Maven 3.9+
- IntelliJ IDEA (recommended)

### 1. Clone the repo

```bash
git clone https://github.com/your-username/bookstore.git
cd bookstore
````

### 2. Generate Code from OpenAPI Spec
    mvn clean compile
This uses the openapi-generator-maven-plugin to generate API interfaces and DTOs from api/api.yaml.

### 3. Run the app
    mvn spring-boot:run
The app runs on http://localhost:8080

# 🧪 API Documentation
Option 1: Static OpenAPI YAML
See api/api.yaml

Option 2: Swagger UI (if Springdoc is enabled)
Visit: http://localhost:8080/swagger-ui.html

🛠️ Development Notes
Generated sources are placed in src/main/java/com/example/bookstore/generated/* for visibility and Git inclusion.

You may choose to generate into target/generated-sources instead if you prefer to exclude generated code from Git.

Use delegatePattern=true for clean separation between interface and implementation.

✅ Todo / Extensions
Add full CRUD for Author and Chapter entities

Add database migration support (Flyway)

Add tests for controllers and services

Deploy to Docker or cloud

📄 License
This project is licensed under the MIT License.

👤 Author
Mohamed Ali Mbarek – @iso-gruppe





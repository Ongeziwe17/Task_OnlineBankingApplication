# 💰 Spring Boot Banking Application

This is a simple **Spring Boot**-based banking application that provides basic functionality for managing bank accounts. It supports common operations such as creating, reading, updating, and deleting bank accounts, as well as handling deposits and withdrawals. All operations are integrated with a **MySQL** database and have been tested using **Postman**.

---

## ✨ Features

- **Create Bank Account**: Add a new bank account with initial details.
- **Read Account by ID**: Retrieve details of a bank account using its unique ID.
- **Update Account**: Modify account holder information or other details.
- **Make Deposits & Withdrawals**: Perform transactions to deposit or withdraw funds.
- **Delete Bank Account**: Remove a bank account from the system.
- **Get All Accounts**: List all bank accounts stored in the database.

---

## 🛠️ Technologies Used

| Tool               | Purpose                                      |
|--------------------|----------------------------------------------|
| IntelliJ Ultimate  | Integrated Development Environment (IDE)     |
| Java               | Programming language                         |
| Maven              | Build automation & dependency management     |
| Spring Boot        | Framework for creating the application       |
| Spring Data JPA    | ORM layer for database interaction           |
| MySQL              | Relational database for persistence          |

---

## 🚀 Getting Started

### ✅ Prerequisites

Ensure you have the following installed:

- **Java Development Kit (JDK)** 8 or higher
- **Maven** 3.6 or higher
- **Spring Boot**
- **MySQL** (Workbench or CLI)

### 🔧 Configuration

Update the `application.properties` file with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/YOUR_DB_NAME
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update


---
Let me know if you want me to include a diagram of the architecture, sample Postman collection!

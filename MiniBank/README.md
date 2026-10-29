# MiniBank — Java Banking Management System

## 1. Project Overview

MiniBank is a console-based banking management application developed using Java. It demonstrates object-oriented programming, generic programming, collections, exception handling, file handling, multithreading, reflection, and unit testing.

The application provides basic banking operations through a menu-driven interface.

## 2. Objectives

* Implement account creation and management.
* Perform deposits, withdrawals, and transfers.
* Store accounts using a reusable generic Repository.
* Use the `@Id` annotation and reflection to identify objects.
* Handle invalid operations and banking errors.
* Support transaction processing using multithreading.
* Save and reload account data.
* Generate transaction reports.
* Test application behavior using JUnit.

## 3. Technologies Used

* Java
* Java Collections Framework
* Generics
* Reflection and custom annotations
* Exception handling
* File I/O and NIO
* Multithreading and ExecutorService
* JUnit 5
* Command-line tools

## 4. Features

1. Open a bank account.
2. Deposit money.
3. Withdraw money.
4. Transfer money between accounts.
5. View an account statement.
6. List all accounts.
7. Store accounts using a generic repository.
8. Validate account information.
9. Process transactions using a thread pool.
10. Save and reload account data.
11. Generate transaction reports.
12. Run automated unit tests.

Only features implemented and tested in the submitted version should be considered complete.

## 5. Project Structure

```text
MiniBank/
├── src/
│   ├── MiniBank.java
│   ├── model/
│   │   ├── Account.java
│   │   ├── Customer.java
│   │   └── annotation/
│   │       └── Id.java
│   ├── repository/
│   │   └── Repository.java
│   ├── service/
│   │   ├── BankService.java
│   │   ├── TransactionProcessor.java
│   │   ├── StatePersister.java
│   │   ├── TransactionLog.java
│   │   └── ReportGenerator.java
│   └── util/
│       └── Validator.java
├── test/
│   └── BankServiceTest.java
├── README.md
├── manifest.txt
└── MiniBank.jar
```

The structure above is an example. Update it to match the actual files in your project.

## 6. Requirements

* Java Development Kit (JDK) installed.
* Java compiler (`javac`) available.
* Java runtime (`java`) available.
* JUnit 5 configured if automated tests are required.

Check your Java installation:

```bash
java -version
javac -version
```

## 7. Compilation

For a project whose source files are stored in `src/`, compile the application from the project root:

```bash
javac -d out -sourcepath src src/MiniBank.java
```

This command assumes that `MiniBank.java` references the application's source dependencies and that all required source files are under `src/`.

If the project uses a build tool or additional runtime dependencies, follow its build configuration instead.

## 8. Execution

Run the compiled application:

```bash
java -cp out MiniBank
```

To run the packaged application:

```bash
java -jar MiniBank.jar
```

The runnable JAR requires a valid manifest that identifies the main class.

## 9. Unit Testing

JUnit tests are used to check important application behavior, including:

* Depositing money increases the account balance.
* Withdrawals that exceed the permitted limit are rejected.
* Invalid operations raise the expected exceptions.
* Concurrent transactions preserve the correct final balance.

Run the tests using the configured test runner or build tool.

For Maven projects, the usual command is:

```bas
mvn test
```

This command requires a correctly configured `pom.xml`, JUnit dependency, and test source directory.

## 10. Data Persistence

The persistence component saves account information and restores it when the application starts, provided these operations are integrated into the main application.

Transaction logs and reports can be generated using the project's file-handling components.

## 11. Testing Checklist

* [ ] Application starts successfully.
* [ ] New accounts can be created.
* [ ] Deposits update balances correctly.
* [ ] Valid withdrawals work.
* [ ] Invalid withdrawals are rejected.
* [ ] Transfers update both account balances correctly.
* [ ] Account statements display the correct information.
* [ ] Account data can be saved and reloaded.
* [ ] Transaction reports are generated correctly.
* [ ] Unit tests pass.
* [ ] Runnable JAR starts successfully.

## 12. Limitations

MiniBank is an educational project. It is not intended for real banking transactions or production use.

Production banking systems require additional security, authentication, authorization, reliable database transactions, auditing, and operational controls.

## 13. Conclusion

MiniBank demonstrates how Java programming concepts can be combined to create a modular banking application. The project applies object-oriented design, generic repositories, validation, exception handling, concurrency, persistence, reporting, and automated testing in a single application.

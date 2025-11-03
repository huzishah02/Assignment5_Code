# Assignment 5 - Unit, Mocking, and Integration Testing

[![SE333 Continuous Integration](https://github.com/huzishah02/Assignment5_Code/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/huzishah02/Assignment5_Code/actions/workflows/SE333_CI.yml)

---

## Project Overview
This project focuses on implementing and automating **unit testing**, **mocking**, and **integration testing** using **JUnit 5** and **Mockito**, followed by setting up **Continuous Integration (CI)** with **GitHub Actions**.  
The goal is to ensure high code quality, proper test coverage, and automated validation of builds for each commit or pull request.

The codebase contains two main components:
1. **Barnes & Noble Package** – used for Part 1 testing practice.  
2. **Amazon Package** – used for integration and unit tests in Part 3.

---

## Part 1 – Project Setup and Testing Practice

### Objective
Practice writing and organizing **specification-based** and **structural-based** tests for the `BarnesAndNoble` package.

### Test Classes Created
- `BookTest.java`  
- `PurchaseSummaryTest.java`  
- `BarnesAndNobleTest.java`

Each test uses display annotations to indicate its testing style:
```java
@DisplayName("specification-based")
@DisplayName("structural-based")

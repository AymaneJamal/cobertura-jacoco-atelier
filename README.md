# 📊 Code Coverage Tools – From Cobertura to JaCoCo

## 🎯 Plan for the Presentation and Workshop

---

## 1. Introduction: The Need for Code Coverage Tools

### 🔍 Key Points:

- **Purpose of Testing:**
  - Ensures code reliability.
  - Helps catch bugs early in the development cycle.

- **Challenges in Testing:**
  - Not all code paths may be covered.
  - Developers often overestimate test coverage.

- **What is Code Coverage?**
  - A metric to measure how much of the source code is executed during tests.
  - Helps identify untested parts of code and improves test quality.

---

## 2. Cobertura: A Legacy Code Coverage Tool

### 🔍 Key Points:

- **What is Cobertura?**
  - An early open-source tool for Java code coverage analysis.
  - Generates line and branch coverage reports.

- **How It Works:**
  - Instruments Java bytecode to track execution during tests.

- **Limitations:**
  - No longer maintained.
  - Incompatible with Java 8+.
  - Performance issues on large projects.

---

## 3. Introduction to JaCoCo: The Successor

### 🔍 Key Points:

- **What is JaCoCo?**
  - A modern Java code coverage tool.
  - Actively maintained and compatible with modern Java.

- **Advantages Over Cobertura:**
  - Lightweight, fast instrumentation.
  - Integration with Maven, Gradle, Jenkins, etc.
  - Supports metrics like method, instruction, and line coverage.
  - Generates detailed HTML reports.

- **Why JaCoCo?**
  - Ideal for CI/CD pipelines.
  - Considered the standard for modern Java projects.

---

## 4. Conclusion: Key Takeaways

### 🔍 Summary:

- **Purpose of Code Coverage:**
  - Offers measurable insights into test effectiveness.
  - Encourages comprehensive test writing.

- **Transition from Cobertura to JaCoCo:**
  - Modern workflows demand modern tools.
  - JaCoCo is the go-to solution today.

- **Next Steps:**
  - Explore the demo project.
  - Understand how to apply JaCoCo in real-world scenarios.

---

## 5. Practical Demonstration – Code Coverage in Action

### 🔧 Introduction to the Demo Project

We created a **Spring Boot** project to demonstrate how to apply code coverage tools like JaCoCo. This project simulates a simple **order processing service**.

#### 📦 Components:

1. `Order`: Represents order details like product ID, quantity, price, and customer ID.
2. `OrderProcessingException`: Custom exception for invalid order data.
3. `OrderService`: Contains business logic for processing orders:
   - Validates order data.
   - Applies business discounts.
   - Updates customer status and balance.
   - Returns processed order details.

---

### 🔍 Features of the Project

- **Discount Logic:**
  - `Bulk Discount`: 10% off for large quantity orders.
  - `VIP Discount`: 5% off for VIP customers.
  - `Seasonal Discount`: 15% off for products starting with "S".

- **Order Validation:**
  - Non-null values.
  - Positive quantity and price.
  - Valid customer IDs.

- **Customer Management:**
  - Tracks customer spending.
  - Automatically promotes to VIP based on thresholds.

---

## 🧪 Steps We Followed

### ✅ Step 1: Creating the Spring Boot Project

- Basic structure includes: `Order`, `OrderProcessingException`, `OrderService`.

---

### ✅ Step 2: Writing Unit Tests

- Using **JUnit 5** to test:
  - Valid orders (happy paths).
  - Invalid inputs (negative cases).
  - Edge scenarios for each discount rule.

---

### ✅ Step 3: Integrating JaCoCo

- JaCoCo integrated via `jacoco-maven-plugin` in `pom.xml`.

#### 🛠 Maven Command to Generate Report:

```bash
mvn clean verify
```
Executes tests and generates HTML report in target/site/jacoco/index.html.

✅ Step 4: Running and Interpreting the Coverage Report
Run tests → Generate report → Open report in browser.

📈 Interpreting the JaCoCo Report
Line Coverage: Code lines executed during tests.

Branch Coverage: All logical branches tested.

Color Indicators:

✅ Green: Fully tested.

❌ Red: Not tested.

⚠️ Yellow: Partially covered (e.g., one side of a conditional tested).

✅ Conclusion
This project provided:

A practical look at how code coverage tools improve test quality.

Insights into migrating from Cobertura to JaCoCo.

A hands-on demonstration of integrating and interpreting JaCoCo results in a Spring Boot app

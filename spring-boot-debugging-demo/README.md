# Spring Boot Order Management — Debugging Demo

An intentionally faulty Spring Boot application with independent debugging exercises for **Eclipse, IntelliJ IDEA, or VS Code**. Import this folder as an independent Maven project. See the [parent README](../README.md) for overall setup and scenario overview.

**Requirements:** JDK 21+, Maven / IDE Maven integration, Spring Boot **3.5.16** (dependencies downloaded on first build).

> **Build from the parent folder:** `mvn clean install` builds both demo applications using the root aggregator `pom.xml`. This project can still be built independently using `mvn clean install` from its own folder.

## Launch and reproduce

- **Main class:** `org.learningfromexperience.demo.boot.OrderDemoApplication`
- **Base URL:** http://localhost:8085/
- From this directory, you may run `mvn spring-boot:run` for a normal smoke test. For debugging, launch the main class using the IDE debugger.
- Open the browser page and choose the named scenario card. **After the agent sets breakpoints**, click that card's submit button again to trigger the relevant HTTP request. The browser will wait while a breakpoint suspends the request thread. Resume execution to allow the response to complete.
- Do not leave a normal server instance using port 8085 running when launching another debug instance.

## S1 — Order total does not include discount

**Browser card:** **Discount calculation** (quantity 2, unit price ₹60, discount 10%).

**Testing prompt:**

> The orders API returns ₹120 instead of ₹108 for quantity 2, unit price ₹60, and a 10% discount. Use the IDE debugger to reproduce the response, follow the relevant execution, inspect actual calculation values, and explain the cause. Do not modify the application code.

**Expected symptom:** The response contains ₹120 instead of ₹108. Investigate via actual suspended frames and values, not source reading alone.

## S2 — Incorrect shipping charge

**Browser card:** **Shipping eligibility** (quantity 2, unit price ₹300, discount 20%).

**Testing prompt:**

> An order qualifies for free shipping, but the application charges a shipping fee. Start a debug session, investigate the problem using the IDE debugger, and identify the root cause. Do not modify the code.

**Expected symptom:** ₹50 shipping is charged despite the original ₹600 subtotal. This is the preferred scenario for demonstrating observations in more than one application class.

## S3 — Valid SKU rejected with HTTP 400

**Browser card:** **Valid SKU rejected** (SKU `SKU-208`, quantity 1).

**Testing prompt:**

> The fulfillment API rejects the apparently valid SKU-208 with HTTP 400. Debug the request, locate where the SKU is rejected, inspect the values at the relevant execution points, and explain why the rejection happens. Do not modify the application code.

**Expected symptom:** HTTP 400 in response to the default request. Capture actual validation state and any relevant exception.

## S5 — Failing Spring-context JUnit test

**Target method:** `org.learningfromexperience.demo.boot.OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints`

**Testing prompt:**

> The Spring-context JUnit test OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints fails for an order amount of ₹250. Launch this specific test method in debug mode, inspect the actual runtime values and failing assertion, and explain the cause using debugger evidence. Do not modify the application code.

**How to trigger:** Locate `src/test/java/org/learningfromexperience/demo/boot/OrderApiIntegrationScenario.java` and debug **this JUnit method**, not the web application. The test intentionally fails. The class is excluded from Maven Surefire's normal naming-based discovery, so `mvn test` is not the trigger for this scenario. Running all tests in the IDE can still pick it up.

## API endpoints (reference)

| Scenario | API | Trigger |
| --- | --- | --- |
| S1 | `POST /api/orders/discount-quote` | Discount calculation card |
| S2 | `POST /api/orders/shipping-quote` | Shipping eligibility card |
| S3 | `POST /api/fulfillment/validate` | Valid SKU rejected card |
| S5 | Spring-context JUnit | Targeted test-method debug launch |

## Debugging trial acceptance

- [ ] Symptom reproduced by the specified browser card or JUnit method
- [ ] Debug session started in the selected IDE (not only a normal server run)
- [ ] Relevant real debugger stops and variable/stack observations captured
- [ ] Explanation reflects runtime evidence, not an assumed root cause
- [ ] Browser requests resumed to completion or their failure recorded
- [ ] No unintended edits; only agent-owned resources cleaned up
- [ ] Reports, where available, accurately distinguish observed evidence from conclusions

**Do not fix the intentional defects in this reusable demo project.** For a proposed-fix exercise, use a copy or separate Git branch.

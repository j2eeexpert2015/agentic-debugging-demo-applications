# Agentic Debugging Demo Applications — complete bundle

**Single download, run one scenario at a time.** The root Maven reactor contains three independent applications: `java-debugging-demo` (J1,J2,J3), `spring-boot-debugging-demo` (S1,S2,S3,S5), and `order-management-service` (O1–O4). Existing baseline defects were preserved. **Petclinic is separate and is not included.**

1. Install JDK 21+ and Maven. From the extracted repository root run `mvn clean install` (Maven must resolve Spring Boot dependencies; first run needs access to your Maven repository/internet).
2. Import the desired module into Eclipse, IntelliJ IDEA or VS Code. Java scenarios use a `main` class, S scenarios run at http://localhost:8085/, O scenarios at http://localhost:8086/.
3. Open **[SCENARIOS.md](SCENARIOS.md)**. Select the next scenario, follow its minimal reproduction step, and copy its short human-like prompt.
4. The agent must actually use the IDE debugger, observe runtime evidence, and avoid modifying code. For O-series click **Reset Demo Data** before each scenario.
5. Track results in **[docs/VALIDATION_TRACKER.md](docs/VALIDATION_TRACKER.md)**. New O-series scenarios are implemented as demonstration defects but **have not yet passed live MCP trials**.

> The original README material follows for existing baseline scenarios. It is retained for continuity. Use SCENARIOS.md as the canonical prompt catalog; S2 retains the exact previously approved wording.

---

## Existing baseline: detailed testing instructions

## Testing procedure (all IDEs)

1. Open **only the intended scenario** and run it normally once to confirm the symptom. Stop the normal JVM before initiating a separate debugger-controlled launch.
2. Copy the **Testing prompt** below (or from the relevant project README) into the chosen agent, with a debugging MCP integration connected. The prompt deliberately does **not** specify breakpoints or a root cause.
3. Allow the agent to select an evidence-driven debugging route. For the Spring Boot browser scenarios, start the application in **Debug** mode and use the browser to trigger the request **after** the agent places breakpoints; the request waits while the JVM is suspended.
4. Evaluate the captured **actual debugger stops, threads/frames, variable values, exception information, and root-cause explanation**. Static code reading alone is not a passed agentic-debugging trial.
5. Verify resumption and cleanup of only agent-owned breakpoints/sessions; keep or export the investigation report if the debugger supports it. Different IDE integrations have different capabilities; do not require identical MCP tool names.

**Do not share answers or expected root causes with the debugging agent.** The prompts below are suitable to copy directly.

## Core Java — original baseline prompts

### J1 — Wrong discount for one customer in a 1,000-order batch

**Entry point:** `org.learningfromexperience.demo.java.batch.BatchDiscountDemo`

**Testing prompt:**

> A batch processes 1,000 orders, but customer CUST-0427 receives an incorrect discount. Use the IDE debugger to reproduce the issue, inspect the relevant runtime values, and explain the cause using debugger evidence. Choose appropriate breakpoints yourself. Do not modify the application code.

**Symptom to observe:** one discount mismatch involving `CUST-0427` in the console output.

### J2 — Silent transaction import loss

**Entry point:** `org.learningfromexperience.demo.java.imports.TransactionImportDemo`

**Testing prompt:**

> A transaction import finishes without displaying an error, but it imports only 11 of 12 input records. Use the IDE debugger to discover which record is lost, investigate what happens to it at runtime, and explain the cause using debugger evidence. Do not modify the application code.

**Symptom to observe:** 12 input records and 11 imported records.

## Spring Boot — original baseline prompts

**Application main class:** `org.learningfromexperience.demo.boot.OrderDemoApplication`

**Browser trigger:** Start the application on port 8085, open **http://localhost:8085/** and use the named scenario card. A browser request paused at a debugger breakpoint will not complete until the JVM is resumed.

### S1 — Incorrect discounted order total

**Browser card:** Discount calculation (defaults: quantity 2, unit price ₹60, discount 10%).

**Testing prompt:**

> The orders API returns ₹120 instead of ₹108 for quantity 2, unit price ₹60, and a 10% discount. Use the IDE debugger to reproduce the response, follow the relevant execution, inspect actual calculation values, and explain the cause. Do not modify the application code.

### S2 — Incorrect shipping charge

**Browser card:** Shipping eligibility (defaults: quantity 2, unit price ₹300, discount 20%).

**Testing prompt:**

> An order qualifies for free shipping, but the application charges a shipping fee. Start a debug session, investigate the problem using the IDE debugger, and identify the root cause. Do not modify the code.

### S3 — Valid SKU rejected with HTTP 400

**Browser card:** Valid SKU rejected (default SKU `SKU-208`, quantity 1).

**Testing prompt:**

> The fulfillment API rejects the apparently valid SKU-208 with HTTP 400. Debug the request, locate where the SKU is rejected, inspect the values at the relevant execution points, and explain why the rejection happens. Do not modify the application code.

### S5 — Spring-context JUnit test failure

**Test method:** `org.learningfromexperience.demo.boot.OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints`

**Testing prompt:**

> The Spring-context JUnit test OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints fails for an order amount of ₹250. Launch this specific test method in debug mode, inspect the actual runtime values and failing assertion, and explain the cause using debugger evidence. Do not modify the application code.

**Important:** S5 is an intentionally failing **JUnit method**. Debug it individually using the IDE; it is not a browser scenario. Its class is named to avoid automatic Maven Surefire test discovery. Running all JUnit tests through the IDE may still execute it.

## Scenario overview

| ID | Project | Entry point / trigger | Expected symptom |
| --- | --- | --- | --- |
| J1 | Core Java | `BatchDiscountDemo.main` | One of 1,000 orders has a discount mismatch |
| J2 | Core Java | `TransactionImportDemo.main` | 11 of 12 transactions imported without an error |
| S1 | Spring Boot | `POST /api/orders/discount-quote` | ₹120 returned instead of ₹108 |
| S2 | Spring Boot | `POST /api/orders/shipping-quote` | ₹50 shipping for original subtotal ₹600 |
| S3 | Spring Boot | `POST /api/fulfillment/validate` | HTTP 400 for `SKU-208` |
| S5 | Spring Boot | Specific JUnit method | Intentionally failing assertion |

## Build / run

### Build all modules together (recommended)

Open a terminal in **`agentic-debugging-demo-applications/`**, the directory containing this root `pom.xml`:

```bash
mvn clean install
```

This runs the Maven reactor for the aggregator and all three modules. The Maven parent relationships within the two demo POMs are unchanged. The applications remain independent Maven projects and may also be imported separately into an IDE.

### Build one application only

From the root folder (Windows Command Prompt or PowerShell):

```bash
mvn -f java-debugging-demo/pom.xml clean install
mvn -f spring-boot-debugging-demo/pom.xml clean install
```

Alternatively, change into either application directory and run `mvn clean install` there.

### Intentional S5 test failure

The Spring-context JUnit scenario **S5** is intentionally faulty. Its class `OrderApiIntegrationScenario` is not selected by Maven Surefire's default test-name patterns; therefore the normal root `mvn clean install` is intended to complete successfully while preserving the bug. Run this test **explicitly** in your IDE's debugger when investigating S5. If you explicitly run the test through Maven, its failure is intentional and the build will fail.

**Do not fix the intentional defects** to make debugging demonstrations pass; a successful Maven build and a successful agentic debugging investigation measure different things.


For the baseline Spring Boot module, to start the web application without the IDE (run from its directory):

```bash
mvn spring-boot:run
```

`mvn clean package` is a build check, **not** a demonstration that the intentional debugging scenarios are fixed. S5 must be invoked as the selected JUnit method in an IDE for its intentional failure. The regular Maven test lifecycle does not automatically discover its class name.

## How to judge a debugging trial

A successful trial uses the IDE debugger to capture runtime evidence, reaches a defensible root-cause explanation, and leaves the application in a known state. For MCP-based investigations also record any unsupported commands, missing observations, or incomplete reporting. **Do not mark an agentic-debugging scenario passed based solely on static source inspection.**

These applications are IDE-agnostic by design. Exact debugging commands and MCP support vary by integration.

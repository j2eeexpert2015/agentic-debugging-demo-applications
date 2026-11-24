# Agentic Debugging Demo Applications

Reusable, **IDE-neutral** agentic Java debugging exercises for Eclipse, IntelliJ IDEA, and Visual Studio Code. Both projects are independent Maven projects; they are designed for AI agents that actively control an IDE debugger through a compatible MCP integration. They can also be debugged manually for comparison.

> **Intentional bugs:** These applications are deliberately incorrect. Do not fix them before recording or running an investigation. The README prompts reveal only the symptoms, not the answers.

## Folder structure

```text
agentic-debugging-demo-applications/
├── pom.xml                         # Root Maven reactor aggregator
├── README.md                       # Scenario prompts and instructions
├── java-debugging-demo/            # Core Java: J1 and J2
│   ├── pom.xml
│   ├── README.md
│   └── src/main/java/...
└── spring-boot-debugging-demo/     # Spring Boot: S1, S2, S3 and S5
    ├── pom.xml
    ├── README.md
    └── src/main/, src/test/...
```

## Requirements

- JDK **21 or newer**, configured as the project JDK.
- Maven 3.9+ recommended (or your IDE's Maven integration). The root `pom.xml` is a **reactor aggregator**, allowing both independent applications to be built together; each application also retains its own `pom.xml`.
- An IDE debugger (Eclipse, IntelliJ IDEA, or VS Code with Java debugging support). Agentic trials require an installed, compatible debugging MCP implementation and a connected agent; these samples do **not** bundle one.
- Spring Boot demo additionally requires Maven dependency resolution; the first build may download dependencies. Default server port: **8085**.

## Importing into your IDE

| IDE | How to open the projects |
| --- | --- |
| Eclipse | **File → Import → Maven → Existing Maven Projects**; select each project directory; ensure JDK 21+ |
| IntelliJ IDEA | **File → Open** on each project directory or `pom.xml`; import as Maven project; select JDK 21+ |
| VS Code | Open each project folder (or this parent folder) with **Extension Pack for Java** / appropriate Java extensions; let Maven import finish |

The projects can live outside the debugger's source repository. If copying them into a debugger repository, keep them separate from its existing fixtures; do not overwrite the existing `examples/` directory.

## Testing procedure (all IDEs)

1. Open **only the intended scenario** and run it normally once to confirm the symptom. Stop the normal JVM before initiating a separate debugger-controlled launch.
2. Copy the **Testing prompt** below (or from the relevant project README) into the chosen agent, with a debugging MCP integration connected. The prompt deliberately does **not** specify breakpoints or a root cause.
3. Allow the agent to select an evidence-driven debugging route. For the Spring Boot browser scenarios, start the application in **Debug** mode and use the browser to trigger the request **after** the agent places breakpoints; the request waits while the JVM is suspended.
4. Evaluate the captured **actual debugger stops, threads/frames, variable values, exception information, and root-cause explanation**. Static code reading alone is not a passed agentic-debugging trial.
5. Verify resumption and cleanup of only agent-owned breakpoints/sessions; keep or export the investigation report if the debugger supports it. Different IDE integrations have different capabilities; do not require identical MCP tool names.

**Do not share answers or expected root causes with the debugging agent.** The prompts below are suitable to copy directly.

## Core Java — testing prompts

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

## Spring Boot — testing prompts

**Application main class:** `org.learningfromexperience.demo.boot.OrderDemoApplication`

**Browser trigger:** Start the application on port 8085, open **http://localhost:8085/** and use the named scenario card. A browser request paused at a debugger breakpoint will not complete until the JVM is resumed.

### S1 — Incorrect discounted order total

**Browser card:** Discount calculation (defaults: quantity 2, unit price ₹60, discount 10%).

**Testing prompt:**

> The orders API returns ₹120 instead of ₹108 for quantity 2, unit price ₹60, and a 10% discount. Use the IDE debugger to reproduce the response, follow the relevant execution, inspect actual calculation values, and explain the cause. Do not modify the application code.

### S2 — Incorrect shipping charge

**Browser card:** Shipping eligibility (defaults: quantity 2, unit price ₹300, discount 20%).

**Testing prompt:**

> An order with an original subtotal of ₹600 is charged ₹50 shipping, even though orders of at least ₹500 qualify for free shipping. Use the IDE debugger to reproduce the problem and follow the calculation across relevant application code, inspecting real runtime values. Explain the cause without modifying the code.

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

### Build both applications together (recommended)

Open a terminal in **`agentic-debugging-demo-applications/`**, the directory containing this root `pom.xml`:

```bash
mvn clean install
```

This runs the Maven reactor for the aggregator and both modules. The Maven parent relationships within the two demo POMs are unchanged. The applications remain independent Maven projects and may also be imported separately into an IDE.

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


For the Spring Boot project, to start the web application without the IDE:

```bash
mvn spring-boot:run
```

`mvn clean package` is a build check, **not** a demonstration that the intentional debugging scenarios are fixed. S5 must be invoked as the selected JUnit method in an IDE for its intentional failure. The regular Maven test lifecycle does not automatically discover its class name.

## How to judge a debugging trial

A successful trial uses the IDE debugger to capture runtime evidence, reaches a defensible root-cause explanation, and leaves the application in a known state. For MCP-based investigations also record any unsupported commands, missing observations, or incomplete reporting. **Do not mark an agentic-debugging scenario passed based solely on static source inspection.**

These applications are IDE-agnostic by design. Exact debugging commands and MCP support vary by integration.

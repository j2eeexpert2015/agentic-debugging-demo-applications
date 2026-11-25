# Core Java Debugging Demo

Two independent, intentionally faulty, dependency-free Core Java programs. Use with **Eclipse, IntelliJ IDEA, or VS Code** (JDK 21+). Import this folder as a Maven project. Both scenarios are also listed in the [parent README](../README.md).

> **Build from the parent folder:** `mvn clean install` builds all demo applications using the root aggregator `pom.xml`. This project can still be built independently using `mvn clean install` from its own folder.

## Setup

```bash
mvn clean package
```

Run the indicated `main` class normally to reproduce its symptom; stop it before starting a separate debugger launch. For agentic trials connect your selected IDE's debugger MCP, paste the testing prompt **without disclosing the defect**, and let the agent decide where to break and inspect.

## Batch Discount Mismatch

**Main class:** `org.learningfromexperience.demo.java.batch.BatchDiscountDemo`

**Observed symptom:** A 1,000-order batch reports one mismatch for `CUST-0427`.

**Testing prompt (copy into your debugging agent):**

> A batch processes 1,000 orders, but customer CUST-0427 receives an incorrect discount. Use the IDE debugger to reproduce the issue, inspect the relevant runtime values, and explain the cause using debugger evidence. Choose appropriate breakpoints yourself. Do not modify the application code.

**What to validate:** A meaningful conditional/targeted breakpoint or equivalent selective debugging strategy; the affected order and actual runtime values; an evidence-based explanation without repeated stops for unrelated records.

## Silent Transaction Import Failure

**Main class:** `org.learningfromexperience.demo.java.imports.TransactionImportDemo`

**Observed symptom:** There are 12 input records but only 11 imported; the program does not print an error.

**Testing prompt (copy into your debugging agent):**

> A transaction import finishes without displaying an error, but it imports only 11 of 12 input records. Use the IDE debugger to discover which record is lost, investigate what happens to it at runtime, and explain the cause using debugger evidence. Do not modify the application code.

**What to validate:** Debugger evidence about the missing record and the handling of its failure, preferably including exception behavior and the relevant stack frame.

## IDE-neutral acceptance checklist

- [ ] Correct `main` class launched under debugger
- [ ] Real stop/inspection evidence captured (not just code reading)
- [ ] Root cause explained from observed values/exception behavior
- [ ] Unrelated breakpoints and launches unaffected
- [ ] Agent-owned resources cleaned up and available report reviewed

No source changes or external services are required for these scenarios.


## Unexpected Order State Mutation

Run `org.learningfromexperience.demo.java.state.OrderStateDemo.main`.

> An order starts with the correct amount, but the amount changes unexpectedly while processing other orders. Debug the application and find out what's changing it. Don't modify the code.

See [`../SCENARIOS.md`](../SCENARIOS.md).

# Agentic Debugging Scenario Catalog

Scenario names describe the user-visible symptom; short, human-like prompts remain intentionally free of root-cause clues. These are developer-style prompts. Give the agent the symptom, not an answer key. **Do not fix source code during the investigation.** A successful trial requires actual debugger suspension and runtime observations, not just source inspection.

## Foundation — Existing debugging scenarios (unchanged intentional defects)

### Batch Discount Mismatch (Core Java)
Run `BatchDiscountDemo.main`. Prompt:
> A batch processes 1,000 customer orders, but customer CUST-0427 gets an incorrect discount. Use the IDE debugger to investigate why this happens. Don't modify the code.

### Silent Transaction Import Failure (Core Java)
Run `TransactionImportDemo.main`. Prompt:
> The transaction import completes without any errors, but only 11 out of 12 records are imported. Debug the application and find out what happened to the missing record. Don't modify the code.

### Incorrect Discounted Order Total (Spring Boot, port 8085)
Open the `Incorrect Discounted Order Total` browser card using its defaults. Prompt:
> An order's total is not being calculated correctly after applying a discount. Debug the application, find out why, and explain the problem. Don't modify the code.

### Incorrect Shipping Charge (Spring Boot, port 8085)
Open the `Incorrect Shipping Charge` browser card using its defaults. Keep the previously approved prompt **exactly**:
> An order qualifies for free shipping, but the application charges a shipping fee. Start a debug session, investigate the problem using the IDE debugger, and identify the root cause. Do not modify the code.

### Valid SKU Rejected (Spring Boot, port 8085)
Open the `Valid SKU Rejected` browser card with `SKU-208`. Prompt:
> The application rejects SKU-208 with HTTP 400, even though it's a valid product code. Debug the request and find out why it gets rejected. Don't modify the code.

### Loyalty Points Test Failure (Spring Boot)
Run *only* `OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints` in debug mode; Maven's normal build deliberately does not discover this intentionally failing test.
> The JUnit test OrderApiIntegrationScenario.awardsProportionalLoyaltyPoints is failing. Run it in debug mode, investigate the failure, and explain what's wrong. Don't modify the code.

## Intermediate — New scenarios (not yet MCP-validated)

### Unexpected Order State Mutation (Core Java)
Run `org.learningfromexperience.demo.java.state.OrderStateDemo.main` and observe the first order changing after other orders are processed.
> An order starts with the correct amount, but the amount changes unexpectedly while processing other orders. Debug the application and find out what's changing it. Don't modify the code.

### Order Update Not Persisted (Order Management Service, port 8086)
Click `Reset Demo Data`; click `Order Update Not Persisted — Delivery update`. Order `ORD-1001` is the reproducible record.
> I updated an order and the API returned success, but when I retrieve the order, the delivery note hasn't changed. Debug it and find out why. Don't modify the code.

## Advanced — New scenarios (not yet MCP-validated)

### Incorrect Inventory Adjustment
Reset demo data; run Incorrect Inventory Adjustment. `ORD-1002` changes from 2 to 3 units of `SKU-208`.
> I increased an order from two items to three, but the available inventory dropped more than expected. Debug the update and find out what happened. Don't modify the code.

### Order Confirmation Rollback
Reset demo data; run Order Confirmation Rollback on `ORD-1003`.
> I tried to confirm an order, but it failed and the order is still unconfirmed. Debug the confirmation and find out why the changes didn't stick. Don't modify the code.

### Duplicate Checkout After Retry
Reset demo data; run Duplicate Checkout After Retry; the browser deliberately simulates a repeated network delivery of the same logical checkout request, using one request key.
> A customer placed an order once, but two orders appeared after a network retry. Debug checkout and explain why the retry wasn't handled safely. Don't modify the code.

### Petclinic Visit Date Mismatch
Use the separate pinned Petclinic fork with its deliberately introduced visit-date issue.
> I entered an earlier date for a pet's visit, but the application saved a different date. Debug the visit submission and find out why. Don't modify the code.

## How to execute fairly

1. Launch only one application and one scenario at a time. Start the chosen app in the IDE **Debug** mode; for HTTP scenarios let the agent configure breakpoints before clicking the scenario's button. 
2. Do not give the agent a method name, suspicious line, or answer key (except where necessary to launch a named JUnit test or Java `main`).
3. Capture actual debugger stops, frame values, errors, timing, and agent-owned resource cleanup.
4. Note any unsupported MCP capability or imperfect result. **Do not reword the prompt solely to make the agent succeed.**
5. Reset Order Management Service data (`POST /api/demo/reset` or the UI Reset button) before each Order Management scenario.

The Order Management browser runs at http://localhost:8086/ and the original Spring Boot browser runs at http://localhost:8085/.

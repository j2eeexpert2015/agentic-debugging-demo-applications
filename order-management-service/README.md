# Order Management Service — realistic Spring Boot debugging lab

A small but layered Spring Boot REST/JPA application with H2 database, domain entities, repositories, application services and a built-in scenario UI. This is **intentionally faulty demonstration code**, not a production API. The API is unrestricted and only suitable for local testing.

Requirements: JDK 21+, Maven, Spring Boot 3.5.16 dependencies. Start `OrderManagementApplication` in your IDE debugger, or from this folder run `mvn spring-boot:run`. Open **http://localhost:8086/**.

Run **Reset Demo Data** before each attempt. It deletes all demo orders and inventory and seeds the fixed baseline again. Do not use against real data. For HTTP investigations, have the agent set breakpoints first and then click the scenario button to reproduce the request. See [`../SCENARIOS.md`](../SCENARIOS.md) for exact short prompts and pass criteria.

## Scenarios

| ID | Symptom | UI | Status |
|---|---|---|---|
| O1 | PUT reports new delivery note, later GET returns old note | O1 card | Implemented, requires end-to-end debugger validation |
| O2 | Quantity increase of one removes too much inventory | O2 card | Implemented, requires end-to-end debugger validation |
| O3 | Order confirmation fails and DB state is unchanged | O3 card | Implemented, requires end-to-end debugger validation |
| O4 | A simulated retry of a logical checkout creates two orders | O4 card | Implemented, requires end-to-end debugger validation |

## Endpoints

- `POST /api/demo/reset` — deterministic reset (local demo only)
- `GET /api/orders/ORD-1001`
- `PUT /api/orders/ORD-1001/delivery-note` JSON `{"deliveryNote":"Ring the bell on arrival"}`
- `PUT /api/orders/ORD-1002/quantity` JSON `{"quantity":3}`
- `GET /api/inventory/SKU-208`
- `POST /api/orders/ORD-1003/confirm`
- `POST /api/checkout` JSON `{"customerId":"CUST-RETRY","requestKey":"checkout-once-001"}`
- `GET /api/customers/CUST-RETRY/orders`

O4 deliberately models transport retry with two HTTP deliveries sharing a logical request key. A genuine one-request/one-response test will **not** demonstrate it. This distinction is important for a fair debugging demonstration.

There are no external infrastructure dependencies. H2 runs in-memory; startup and `POST /api/demo/reset` recreate deterministic data. **Do not modify the app during debugging experiments.**

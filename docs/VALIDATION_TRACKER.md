# IDE debugging validation tracker

This records only supported verification, never guessed results.

| Scenario | Source included | Symptom verified with local runtime here | Eclipse MCP trial | IntelliJ MCP trial | VS Code MCP trial |
|---|---|---|---|---|---|
| Batch Discount Mismatch | yes | not rerun in this bundle | pending | pending | pending |
| Silent Transaction Import Failure | yes | not rerun in this bundle | pending | pending | pending |
| Incorrect Discounted Order Total | yes | not rerun in this bundle | pending | pending | pending |
| Incorrect Shipping Charge | yes | not rerun in this bundle | earlier successful Eclipse report (different run) | pending | pending |
| Valid SKU Rejected | yes | not rerun in this bundle | pending | pending | pending |
| Loyalty Points Test Failure | yes | not rerun in this bundle | pending | pending | pending |
| Unexpected Order State Mutation | yes | compiled and reproduced with Java 21 CLI | pending | pending | pending |
| Order Update Not Persisted | yes | requires Spring Boot runtime check | pending | pending | pending |
| Incorrect Inventory Adjustment | yes | requires Spring Boot runtime check | pending | pending | pending |
| Order Confirmation Rollback | yes | requires Spring Boot runtime check | pending | pending | pending |
| Duplicate Checkout After Retry | yes | requires Spring Boot runtime check | pending | pending | pending |
| Petclinic Visit Date Mismatch | no (separate Petclinic fork) | not bundled | pending | pending | pending |

Record: date/time, commit or ZIP checksum, IDE version, model, prompt, launch, actual breakpoint stops, values, outcome, failed MCP calls, ownership/cleanup, attached HTML report.

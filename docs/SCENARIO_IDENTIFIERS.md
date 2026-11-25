# Scenario Identifiers

Use descriptive names in the UI, prompts, and reports. For automation, prefer descriptive, stable slugs rather than opaque letters/numbers. These identifiers are documentation conventions; application endpoints and Java entry points are unchanged.

| Scenario | Stable identifier | Previous label (reference only) |
|---|---|---|
| Batch Discount Mismatch | `batch-discount-mismatch` | `J1` |
| Silent Transaction Import Failure | `silent-transaction-import` | `J2` |
| Unexpected Order State Mutation | `unexpected-order-state-mutation` | `J3` |
| Incorrect Discounted Order Total | `incorrect-discounted-order-total` | `S1` |
| Incorrect Shipping Charge | `incorrect-shipping-charge` | `S2` |
| Valid SKU Rejected | `valid-sku-rejected` | `S3` |
| Loyalty Points Test Failure | `loyalty-points-test-failure` | `S5` |
| Order Update Not Persisted | `order-update-not-persisted` | `O1` |
| Incorrect Inventory Adjustment | `incorrect-inventory-adjustment` | `O2` |
| Order Confirmation Rollback | `order-confirmation-rollback` | `O3` |
| Duplicate Checkout After Retry | `duplicate-checkout-after-retry` | `O4` |
| Petclinic Visit Date Mismatch | `petclinic-visit-date-mismatch` | `P1` |

The previous labels are shown here only to correlate earlier debugging reports and notes. Do not use them as primary display names.

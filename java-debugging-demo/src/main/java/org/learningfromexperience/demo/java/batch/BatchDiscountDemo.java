package org.learningfromexperience.demo.java.batch;

import java.math.BigDecimal;

public final class BatchDiscountDemo {
    public static void main(String[] args) {
        var orders = new CustomerDirectory().loadOrders();
        var pricing = new BatchPricingService();
        int mismatches = 0;
        for (CustomerOrder order : orders) {
            BigDecimal actual = pricing.discountedTotal(order);
            // Customer-facing expected totals are produced independently of actual pricing.
            BigDecimal expected = expectedAmount(order.customerId());
            if (actual.compareTo(expected) != 0) {
                mismatches++;
                System.out.printf("DISCOUNT MISMATCH customer=%s expected=%s actual=%s%n",
                        order.customerId(), expected, actual);
            }
        }
        System.out.printf("Processed %d orders; discount mismatches=%d%n", orders.size(), mismatches);
    }

    private static BigDecimal expectedAmount(String customerId) {
        int id = Integer.parseInt(customerId.substring(5));
        return (id % 5 == 0) ? new BigDecimal("90.00") : new BigDecimal("85.00");
    }
}

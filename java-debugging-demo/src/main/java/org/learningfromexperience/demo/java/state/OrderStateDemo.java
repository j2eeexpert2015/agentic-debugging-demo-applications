package org.learningfromexperience.demo.java.state;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** Launch this class to reproduce J3; no third-party dependencies. */
public final class OrderStateDemo {
    public static void main(String[] args) {
        List<BatchOrder> orders = new ArrayList<>();
        BatchOrderBuilder builder = new BatchOrderBuilder();
        for (int i = 1; i <= 25; i++) {
            orders.add(builder.newOrder("ORDER-%03d".formatted(i), new BigDecimal("100.00")));
        }
        BatchOrder first = orders.getFirst();
        System.out.println("First order before processing: " + first.id() + " amount=" + first.amount());
        new BatchProcessor().applyHandlingFees(orders);
        System.out.println("First order after processing: " + first.id() + " amount=" + first.amount());
        System.out.println("Expected first order amount after its own ₹5 fee: 105.00");
    }
}

package org.learningfromexperience.demo.java.batch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class CustomerDirectory {
    public List<CustomerOrder> loadOrders() {
        List<CustomerOrder> orders = new ArrayList<>(1_000);
        for (int number = 1; number <= 1_000; number++) {
            String customerId = "CUST-%04d".formatted(number);
            String membershipCode = number % 5 == 0 ? "SILVER" : "GOLD";
            if (number == 427) membershipCode = "GOLD ";
            orders.add(new CustomerOrder(customerId, membershipCode, BigDecimal.valueOf(100)));
        }
        return List.copyOf(orders);
    }
}

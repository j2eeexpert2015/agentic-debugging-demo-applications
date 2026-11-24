package org.learningfromexperience.demo.java.batch;

import java.math.BigDecimal;

public final class DiscountPolicy {
    public BigDecimal discountRate(String membershipCode) {
        return switch (membershipCode) {
            case "GOLD" -> new BigDecimal("0.15");
            case "SILVER" -> new BigDecimal("0.10");
            default -> new BigDecimal("0.05");
        };
    }
}

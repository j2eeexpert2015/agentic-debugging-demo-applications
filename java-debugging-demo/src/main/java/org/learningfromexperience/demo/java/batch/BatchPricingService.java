package org.learningfromexperience.demo.java.batch;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class BatchPricingService {
    private final DiscountPolicy policy = new DiscountPolicy();

    public BigDecimal discountedTotal(CustomerOrder order) {
        BigDecimal rate = policy.discountRate(order.membershipCode());
        BigDecimal discount = order.merchandiseTotal().multiply(rate);
        return order.merchandiseTotal().subtract(discount).setScale(2, RoundingMode.HALF_UP);
    }
}

package org.learningfromexperience.demo.boot.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class DiscountCalculator {
    public BigDecimal calculateDiscount(BigDecimal subtotal, int discountPercent) {
        int rate = discountPercent / 100;
        return subtotal.multiply(BigDecimal.valueOf(rate)).setScale(2, RoundingMode.HALF_UP);
    }
}

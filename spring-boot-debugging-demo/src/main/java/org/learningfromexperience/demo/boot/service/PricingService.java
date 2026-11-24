package org.learningfromexperience.demo.boot.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public BigDecimal calculateDiscount(BigDecimal subtotal, int percent) {
        BigDecimal rate = BigDecimal.valueOf(percent).movePointLeft(2);
        return subtotal.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }
}

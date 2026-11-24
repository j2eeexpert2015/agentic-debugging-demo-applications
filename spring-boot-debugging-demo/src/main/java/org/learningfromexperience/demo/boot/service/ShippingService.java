package org.learningfromexperience.demo.boot.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {
    private static final BigDecimal FREE_SHIPPING_THRESHOLD = new BigDecimal("500.00");
    private static final BigDecimal STANDARD_SHIPPING = new BigDecimal("50.00");

    public BigDecimal calculateCharge(BigDecimal eligibleAmount) {
        return eligibleAmount.compareTo(FREE_SHIPPING_THRESHOLD) >= 0
                ? BigDecimal.ZERO.setScale(2) : STANDARD_SHIPPING;
    }
}

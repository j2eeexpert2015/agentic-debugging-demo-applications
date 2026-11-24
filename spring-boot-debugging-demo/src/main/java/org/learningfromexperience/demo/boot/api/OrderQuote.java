package org.learningfromexperience.demo.boot.api;

import java.math.BigDecimal;

public record OrderQuote(BigDecimal merchandiseSubtotal, BigDecimal discount,
                         BigDecimal discountedSubtotal, BigDecimal shippingCharge, BigDecimal finalTotal) {}

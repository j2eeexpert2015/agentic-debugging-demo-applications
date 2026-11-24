package org.learningfromexperience.demo.boot.api;

import java.math.BigDecimal;

public record OrderRequest(int quantity, BigDecimal unitPrice, int discountPercent) {}

package org.learningfromexperience.demo.java.batch;

import java.math.BigDecimal;

public record CustomerOrder(String customerId, String membershipCode, BigDecimal merchandiseTotal) {}

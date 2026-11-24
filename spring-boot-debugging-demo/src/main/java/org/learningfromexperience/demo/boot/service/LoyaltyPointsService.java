package org.learningfromexperience.demo.boot.service;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoyaltyPointsService {
    private final int pointsPerHundred;

    public LoyaltyPointsService(@Value("${loyalty.points.per-hundred:10}") int pointsPerHundred) {
        this.pointsPerHundred = pointsPerHundred;
    }

    public int pointsFor(BigDecimal eligibleAmount) {
        if (eligibleAmount == null || eligibleAmount.signum() < 0) {
            throw new IllegalArgumentException("Invalid eligible amount");
        }
        int completeHundreds = eligibleAmount.intValue() / 100;
        return completeHundreds * pointsPerHundred;
    }
}

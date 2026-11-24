package org.learningfromexperience.demo.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.learningfromexperience.demo.boot.service.LoyaltyPointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * An intentionally failing demonstration scenario. The name does not match
 * Maven Surefire's default *Test / Test* patterns. Launch the single method
 * from Eclipse's JUnit debugger when demonstrating the scenario.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderApiIntegrationScenario {
    @Autowired private LoyaltyPointsService loyaltyPointsService;

    @Test
    void awardsProportionalLoyaltyPoints() {
        BigDecimal eligibleAmount = new BigDecimal("250.00");
        int actualPoints = loyaltyPointsService.pointsFor(eligibleAmount);
        assertEquals(25, actualPoints, "Ten points per ₹100, proportional to eligible spend");
    }
}

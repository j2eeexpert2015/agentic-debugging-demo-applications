package org.learningfromexperience.demo.java.state;
import java.math.BigDecimal;
import java.util.List;
public final class BatchProcessor {
    public void applyHandlingFees(List<BatchOrder> orders) {
        for (BatchOrder order : orders) {
            order.amountHolder().add(new BigDecimal("5.00"));
        }
    }
}

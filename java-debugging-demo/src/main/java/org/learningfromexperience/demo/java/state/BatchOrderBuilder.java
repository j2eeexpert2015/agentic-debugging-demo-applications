package org.learningfromexperience.demo.java.state;
import java.math.BigDecimal;
public final class BatchOrderBuilder {
    private final MutableAmount reusableAmount = new MutableAmount();
    public BatchOrder newOrder(String id, BigDecimal amount) {
        reusableAmount.set(amount);
        return new BatchOrder(id, reusableAmount);
    }
}

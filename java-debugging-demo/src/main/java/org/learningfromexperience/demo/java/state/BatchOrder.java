package org.learningfromexperience.demo.java.state;
import java.math.BigDecimal;
public record BatchOrder(String id, MutableAmount amountHolder) {
    public BigDecimal amount() { return amountHolder.get(); }
}

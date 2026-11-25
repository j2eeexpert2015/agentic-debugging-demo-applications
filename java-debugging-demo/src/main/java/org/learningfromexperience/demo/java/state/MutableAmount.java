package org.learningfromexperience.demo.java.state;
import java.math.BigDecimal;
public final class MutableAmount {
    private BigDecimal value = BigDecimal.ZERO;
    public void set(BigDecimal value) { this.value = value; }
    public void add(BigDecimal increment) { value = value.add(increment); }
    public BigDecimal get() { return value; }
}

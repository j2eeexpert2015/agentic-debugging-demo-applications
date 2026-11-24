package org.learningfromexperience.demo.java.imports;

import java.math.BigDecimal;

public final class TransactionParser {
    public BigDecimal parseAmount(String record) {
        String[] fields = record.split(",", -1);
        if (fields.length != 2) throw new InvalidTransactionAmountException("Unexpected transaction format");
        try {
            return new BigDecimal(fields[1].trim());
        } catch (NumberFormatException error) {
            throw new InvalidTransactionAmountException("Invalid amount for transaction " + fields[0]);
        }
    }
}

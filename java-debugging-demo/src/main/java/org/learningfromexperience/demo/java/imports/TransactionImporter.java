package org.learningfromexperience.demo.java.imports;

import java.math.BigDecimal;
import java.util.List;

public final class TransactionImporter {
    private final TransactionParser parser = new TransactionParser();

    public ImportResult importRecords(List<String> records) {
        int imported = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (String record : records) {
            try {
                BigDecimal amount = parser.parseAmount(record);
                total = total.add(amount);
                imported++;
            } catch (InvalidTransactionAmountException ignored) {
            }
        }
        return new ImportResult(imported, total);
    }

    public record ImportResult(int imported, BigDecimal total) {}
}

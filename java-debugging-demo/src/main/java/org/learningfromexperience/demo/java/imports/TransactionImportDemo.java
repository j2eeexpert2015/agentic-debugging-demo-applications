package org.learningfromexperience.demo.java.imports;

import java.util.List;

public final class TransactionImportDemo {
    public static void main(String[] args) {
        List<String> records = List.of(
                "TX-001,120.50", "TX-002,15.00", "TX-003,25.00", "TX-004,9.25",
                "TX-005,100.00", "TX-006,12.00", "TX-007,38.40", "TX-008,21.00",
                "TX-009,18.70", "TX-010,N/A", "TX-011,32.00", "TX-012,10.00");
        TransactionImporter.ImportResult result = new TransactionImporter().importRecords(records);
        System.out.printf("Input records: %d%nImported records: %d%nTotal: %s%n",
                records.size(), result.imported(), result.total());
    }
}

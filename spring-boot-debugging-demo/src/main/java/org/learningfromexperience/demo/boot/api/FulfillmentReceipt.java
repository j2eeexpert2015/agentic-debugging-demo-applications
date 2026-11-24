package org.learningfromexperience.demo.boot.api;

public record FulfillmentReceipt(String validatedSku, int reservedQuantity, String status) {}

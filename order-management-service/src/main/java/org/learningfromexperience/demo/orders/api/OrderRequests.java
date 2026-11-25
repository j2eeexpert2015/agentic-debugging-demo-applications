package org.learningfromexperience.demo.orders.api;
public final class OrderRequests {
    private OrderRequests() {}
    public record UpdateNote(String deliveryNote) {}
    public record UpdateQuantity(int quantity) {}
    public record Checkout(String customerId,String requestKey) {}
}

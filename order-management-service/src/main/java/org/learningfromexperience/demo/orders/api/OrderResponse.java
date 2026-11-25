package org.learningfromexperience.demo.orders.api;
import org.learningfromexperience.demo.orders.domain.CustomerOrder;
public record OrderResponse(String id,String customerId,String deliveryNote,int quantity,boolean confirmed) {
    public static OrderResponse from(CustomerOrder o){return new OrderResponse(o.getId(),o.getCustomerId(),o.getDeliveryNote(),o.getQuantity(),o.isConfirmed());}
}

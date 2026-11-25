package org.learningfromexperience.demo.orders.domain;
import jakarta.persistence.*;
@Entity @Table(name="customer_orders")
public class CustomerOrder {
    @Id private String id;
    private String customerId;
    private String deliveryNote;
    private int quantity;
    private boolean confirmed;
    protected CustomerOrder() {}
    public CustomerOrder(String id, String customerId, String deliveryNote, int quantity) {
        this.id=id;this.customerId=customerId;this.deliveryNote=deliveryNote;this.quantity=quantity;
    }
    public String getId() {return id;}
    public String getCustomerId() {return customerId;}
    public String getDeliveryNote(){return deliveryNote;}
    public void setDeliveryNote(String note){this.deliveryNote=note;}
    public int getQuantity(){return quantity;}
    public void setQuantity(int quantity){this.quantity=quantity;}
    public boolean isConfirmed(){return confirmed;}
    public void setConfirmed(boolean confirmed){this.confirmed=confirmed;}
}

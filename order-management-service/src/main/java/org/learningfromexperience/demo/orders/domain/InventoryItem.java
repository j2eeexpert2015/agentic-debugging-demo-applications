package org.learningfromexperience.demo.orders.domain;
import jakarta.persistence.*;
@Entity @Table(name="inventory_items")
public class InventoryItem {
    @Id private String sku;
    private int available;
    protected InventoryItem() {}
    public InventoryItem(String sku,int available){this.sku=sku;this.available=available;}
    public String getSku(){return sku;}
    public int getAvailable(){return available;}
    public void setAvailable(int quantity){this.available=quantity;}
}

package org.learningfromexperience.demo.orders.application;
import org.learningfromexperience.demo.orders.domain.InventoryItem;
import org.learningfromexperience.demo.orders.persistence.InventoryRepository;
import org.springframework.stereotype.Service;
@Service
public class InventoryService {
    private final InventoryRepository inventory;
    public InventoryService(InventoryRepository inventory){this.inventory=inventory;}
    public int available(String sku){return inventory.findById(sku).orElseThrow().getAvailable();}
    public void adjustAfterQuantityChange(String sku,int oldQuantity,int newQuantity){
        InventoryItem item=inventory.findById(sku).orElseThrow();
        if(newQuantity>item.getAvailable()+oldQuantity) throw new IllegalArgumentException("Not enough stock");
        item.setAvailable(item.getAvailable()-newQuantity);
        inventory.save(item);
    }
}

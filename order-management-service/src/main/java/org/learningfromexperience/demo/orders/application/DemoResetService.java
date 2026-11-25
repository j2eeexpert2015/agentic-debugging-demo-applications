package org.learningfromexperience.demo.orders.application;
import org.learningfromexperience.demo.orders.domain.*;
import org.learningfromexperience.demo.orders.persistence.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class DemoResetService implements ApplicationRunner {
    private final OrderRepository orders;
    private final InventoryRepository inventory;
    public DemoResetService(OrderRepository orders,InventoryRepository inventory){this.orders=orders;this.inventory=inventory;}
    @Override public void run(ApplicationArguments args){reset();}
    @Transactional public void reset(){
        orders.deleteAllInBatch();inventory.deleteAllInBatch();
        orders.save(new CustomerOrder("ORD-1001","CUST-100","Leave at reception",2));
        orders.save(new CustomerOrder("ORD-1002","CUST-200","Standard delivery",2));
        orders.save(new CustomerOrder("ORD-1003","CUST-300","Standard delivery",1));
        inventory.save(new InventoryItem("SKU-208",48));
    }
}

package org.learningfromexperience.demo.orders.application;
import org.learningfromexperience.demo.orders.api.OrderResponse;
import org.learningfromexperience.demo.orders.domain.CustomerOrder;
import org.learningfromexperience.demo.orders.persistence.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class OrderApplicationService {
    private final OrderRepository orders;
    private final InventoryService inventory;
    public OrderApplicationService(OrderRepository orders,InventoryService inventory){this.orders=orders;this.inventory=inventory;}
    @Transactional(readOnly=true) public OrderResponse get(String id){return OrderResponse.from(orders.findById(id).orElseThrow());}
    @Transactional public OrderResponse updateDeliveryNote(String id,String note){
        CustomerOrder order=orders.findById(id).orElseThrow();
        String acceptedNote=note == null ? "" : note.trim();
        if(acceptedNote.length()>120) throw new IllegalArgumentException("Delivery note too long");
        // The request passes validation; follow subsequent persistence behavior through the debugger.
        orders.save(order);
        return new OrderResponse(order.getId(),order.getCustomerId(),acceptedNote,order.getQuantity(),order.isConfirmed());
    }
    @Transactional public OrderResponse updateQuantity(String id,int quantity){
        if(quantity<=0) throw new IllegalArgumentException("Quantity must be positive");
        CustomerOrder order=orders.findById(id).orElseThrow();
        int previousQuantity=order.getQuantity();
        inventory.adjustAfterQuantityChange("SKU-208",previousQuantity,quantity);
        order.setQuantity(quantity);
        return OrderResponse.from(orders.save(order));
    }
}

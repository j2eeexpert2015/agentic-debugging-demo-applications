package org.learningfromexperience.demo.orders.api;
import java.util.List;
import java.util.Map;
import org.learningfromexperience.demo.orders.application.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api")
public class OrderController {
    private final OrderApplicationService orders;
    private final InventoryService inventory;
    private final ConfirmationService confirmation;
    private final CheckoutService checkout;
    private final DemoResetService reset;
    public OrderController(OrderApplicationService orders,InventoryService inventory,ConfirmationService confirmation,CheckoutService checkout,DemoResetService reset){
        this.orders=orders;this.inventory=inventory;this.confirmation=confirmation;this.checkout=checkout;this.reset=reset;
    }
    @GetMapping("/orders/{id}") public OrderResponse get(@PathVariable String id){return orders.get(id);}
    @PutMapping("/orders/{id}/delivery-note") public OrderResponse note(@PathVariable String id,@RequestBody OrderRequests.UpdateNote body){return orders.updateDeliveryNote(id,body.deliveryNote());}
    @PutMapping("/orders/{id}/quantity") public OrderResponse quantity(@PathVariable String id,@RequestBody OrderRequests.UpdateQuantity body){return orders.updateQuantity(id,body.quantity());}
    @GetMapping("/inventory/{sku}") public Map<String,Object> inventory(@PathVariable String sku){return Map.of("sku",sku,"available",inventory.available(sku));}
    @PostMapping("/orders/{id}/confirm") public Map<String,String> confirm(@PathVariable String id){return confirmation.confirm(id);}
    @PostMapping("/checkout") public Map<String,String> checkout(@RequestBody OrderRequests.Checkout body){return Map.of("orderId",checkout.place(body.customerId(),body.requestKey()));}
    @GetMapping("/customers/{id}/orders") public List<String> ordersForCustomer(@PathVariable String id){return checkout.ordersForCustomer(id);}
    @PostMapping("/demo/reset") public Map<String,String> reset(){reset.reset();return Map.of("status","reset");}
}

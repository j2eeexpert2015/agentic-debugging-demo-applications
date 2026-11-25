package org.learningfromexperience.demo.orders.application;
import java.util.List;
import java.util.UUID;
import org.learningfromexperience.demo.orders.domain.CustomerOrder;
import org.learningfromexperience.demo.orders.persistence.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CheckoutService {
    private final OrderRepository orders;
    public CheckoutService(OrderRepository orders){this.orders=orders;}
    @Transactional public String place(String customerId,String requestKey){
        if(customerId==null||customerId.isBlank()||requestKey==null||requestKey.isBlank()) throw new IllegalArgumentException("customerId and requestKey are required");
        String id="WEB-"+UUID.randomUUID();
        orders.save(new CustomerOrder(id,customerId,"",1));
        return id;
    }
    @Transactional(readOnly=true) public List<String> ordersForCustomer(String customerId){
        return orders.findByCustomerId(customerId).stream().map(CustomerOrder::getId).sorted().toList();
    }
}

package org.learningfromexperience.demo.orders.application;
import org.learningfromexperience.demo.orders.domain.CustomerOrder;
import org.learningfromexperience.demo.orders.persistence.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class ReservationService {
    private final OrderRepository orders;
    public ReservationService(OrderRepository orders){this.orders=orders;}
    @Transactional public void reserveAndAudit(String id){
        CustomerOrder order=orders.findById(id).orElseThrow();
        order.setConfirmed(true);
        orders.save(order);
        throw new IllegalStateException("Audit service temporarily unavailable");
    }
}

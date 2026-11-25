package org.learningfromexperience.demo.orders.persistence;
import java.util.List;
import org.learningfromexperience.demo.orders.domain.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderRepository extends JpaRepository<CustomerOrder,String> {
    List<CustomerOrder> findByCustomerId(String customerId);
}

package org.learningfromexperience.demo.orders.persistence;
import org.learningfromexperience.demo.orders.domain.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryRepository extends JpaRepository<InventoryItem,String> {}

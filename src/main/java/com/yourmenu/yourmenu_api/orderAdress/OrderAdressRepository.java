package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderAdressRepository extends JpaRepository<OrderAdress, Long> {

   boolean existsByOrderId(Long orderId);

   Optional<OrderAdress> findByOrderId(Long orderId);

   Long order(Order order);
}

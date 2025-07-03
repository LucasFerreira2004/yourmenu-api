package com.yourmenu.yourmenu_api.orderAdress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderAdressRepository extends JpaRepository<OrderAdress, Long> {

   boolean existsByOrderId(Long orderId);

   OrderAdress findByOrderId(Long orderId);
}

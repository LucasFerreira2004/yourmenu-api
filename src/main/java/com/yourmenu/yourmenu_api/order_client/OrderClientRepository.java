package com.yourmenu.yourmenu_api.order_client;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderClientRepository extends JpaRepository<OrderClient, Long> {
    Optional<OrderClient> findByOrderId(Long orderId);

}

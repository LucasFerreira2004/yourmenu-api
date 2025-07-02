package com.yourmenu.yourmenu_api.orderItem;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderItemRepository {

    @Query(nativeQuery = true, value = """
        SELECT * FROM order_item
        WHERE order_id = :orderId;
    """)
    List<OrderItem> findAllByOrderId(Long orderId);
}

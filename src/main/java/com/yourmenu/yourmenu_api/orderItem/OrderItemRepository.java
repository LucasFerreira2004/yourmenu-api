package com.yourmenu.yourmenu_api.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM order_item
        WHERE order_id = :orderId;
    """)
    List<OrderItem> findAllByOrderId(Long orderId);

    @Query(nativeQuery = true, value = """
        SELECT :quantity * dish_option.price 
        FROM dish_size_option as dish_option
        where dish_option.id = :dishSizeOptionId 
    """)
    BigDecimal findPriceByDishSizeOptionId(Integer quantity, Long dishSizeOptionId);

    @Query(nativeQuery = true, value = """
        SELECT SUM(item.quantity * dish_option.price) 
        FROM order_item as item
        JOIN dish_size_option as dish_option on item.dish_size_option_id = dish_option.id
        JOIN order on order.id = :orderId;
    """)
    BigDecimal findOrderPriceByOrderId (Long orderId);
    
    @Modifying
    @Query("DELETE FROM OrderItem oi WHERE oi.order.restaurant.id = :restaurantId")
    void deleteAllByRestaurantId(@Param("restaurantId") String restaurantId);
}

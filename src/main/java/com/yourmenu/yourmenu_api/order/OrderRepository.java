package com.yourmenu.yourmenu_api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = """
        select * from orders where restaurant_id = :restaurantId order by date_time desc;
    """)
    public List<Order> findAllByRestaurantIdOrderByDateTime(String restaurantId);

    @Query(nativeQuery = true, value = """
        select * from orders 
        where restaurant_id = :restaurantId and date_time >= :dateTime;
        order by date_time desc;
    """)
    public List<Order> findAllByRestaurantAndDateTime(String restaurantId, LocalDateTime dateTime);
}

package com.yourmenu.yourmenu_api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = """
        select * from order where restaurant_id = :restaurantId order by date desc;
    """)
    public List<Order> findAllByRestaurantIdOrderByDate(String restaurantId);
}

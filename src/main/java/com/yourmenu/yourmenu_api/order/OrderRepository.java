package com.yourmenu.yourmenu_api.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(nativeQuery = true, value = """
        select * from orders where restaurant_id = :restaurantId order by date_time desc;
    """)
    public List<Order> findAllByRestaurantIdOrderByDateTime(String restaurantId);

    @Query(nativeQuery = true, value = """
        select * from orders 
        where restaurant_id = :restaurantId 
              and date_time >= :startOfDay and date_time < :endOfDay
        order by date_time desc;
    """)
    public List<Order> findAllByRestaurantAndDate(String restaurantId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query(nativeQuery = true, value = """
        select * from orders where id = :orderId and restaurant_id = :restaurantId;
    """)
    Order findByIdByRestaurant(Long orderId, String restaurantId);

    @Query(nativeQuery = true, value= """
        select sum(price)
        from orders
        where date_time between :startOfDay and :endOfDay;
    """)
    BigDecimal findRevenueByRestaurantAndDate(String restaurantId, LocalDateTime startOfDay, LocalDateTime endOfDay);

    @Query(nativeQuery = true, value= """
        select count(id)
        from orders
        where date_time between :startOfDay and :endOfDay;
    """)
    Long findQtdOrdersByRestaurantAndDate(String restaurantId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}

package com.yourmenu.yourmenu_api.restaurantAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Integer> {
    @Query(nativeQuery = true, value = """
        select * from restaurant_address where restaurant_id = :restaurantId;
    """)
    RestaurantAddress findByRestaurantId(String restaurantId);
    
    @Modifying
    @Query("DELETE FROM RestaurantAddress ra WHERE ra.restaurant.id = :restaurantId")
    void deleteByRestaurantId(@Param("restaurantId") String restaurantId);
}

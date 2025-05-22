package com.yourmenu.yourmenu_api.restaurantAddress;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantAdressRepository extends JpaRepository<RestaurantAddress, Integer> {
    @Query(nativeQuery = true, value = """
        select * from restaurant_adress where restaurant_id = :restaurantId;
    """)
    RestaurantAddress findByRestaurantId(String restaurantId);
}

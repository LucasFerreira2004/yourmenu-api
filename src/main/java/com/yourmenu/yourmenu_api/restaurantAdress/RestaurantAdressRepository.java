package com.yourmenu.yourmenu_api.restaurantAdress;

import com.yourmenu.yourmenu_api.restaurantAdress.mapper.RestaurantAdressMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantAdressRepository extends JpaRepository<RestaurantAdress, Integer> {
    @Query(nativeQuery = true, value = """
        select * from restaurant_adress where restaurant_id = :restaurantId;
    """)
    RestaurantAdress findByRestaurantId(String restaurantId);
}

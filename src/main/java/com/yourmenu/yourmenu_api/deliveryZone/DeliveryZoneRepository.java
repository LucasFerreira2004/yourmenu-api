package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, Long> {
    Optional<DeliveryZone> findByZone(String zoneName);

    Optional<DeliveryZone> findByZoneAndRestaurant(String zoneName, Restaurant restaurant);
    
    @Modifying
    @Query("DELETE FROM DeliveryZone dz WHERE dz.restaurant.id = :restaurantId")
    void deleteAllByRestaurantId(@Param("restaurantId") String restaurantId);
}

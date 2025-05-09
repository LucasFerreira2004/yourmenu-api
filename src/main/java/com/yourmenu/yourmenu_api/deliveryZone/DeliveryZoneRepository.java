package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, Long> {
    Optional<DeliveryZone> findByZone(String zoneName);

    Optional<DeliveryZone> findByZoneAndRestaurant(String zoneName, Restaurant restaurant);
}

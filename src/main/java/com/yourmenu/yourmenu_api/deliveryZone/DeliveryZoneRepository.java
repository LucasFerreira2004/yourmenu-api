package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import io.swagger.v3.core.util.AnnotationsUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryZoneRepository extends JpaRepository<DeliveryZone, Long> {
    Optional<DeliveryZone> findByZone(String zoneName);

    Optional<DeliveryZone> findByZoneAndRestaurant(String zoneName, Restaurant restaurant);

    List<DeliveryZone> findAllByRestaurantId(String restaurantId);
}

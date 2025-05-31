package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneAlreadyExistsException;
import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.DeniedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryZoneValidateService {
    
    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validateRestaurantOwnerShip(String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository
                                    .findById(restaurantId)
                                    .orElseThrow(() -> new RestaurantNotFoundException("restaurantId"));

        if (!restaurant.getAdministrator().getId().equals(adminId)) throw new DeniedAccessException("AdminId");
    }

    public void validateRestaurantOwnerShipPerDeliveryZone(Long deliveryZoneId, String adminId) {
        String restaurantId = deliveryZoneRepository
                .findById(deliveryZoneId)
                .orElseThrow(() -> new DeliveryZoneNotFoundException("id"))
                .getRestaurant()
                .getId();

        validateRestaurantOwnerShip(restaurantId, adminId);
    }

    public void existentDeliveryZone(DeliveryZone deliveryZone, String zoneName, Restaurant restaurant) {
        Optional<DeliveryZone> existingZone = deliveryZoneRepository.findByZoneAndRestaurant(zoneName, restaurant);

        if (existingZone.isPresent() && !existingZone.get().getId().equals(deliveryZone.getId())) {
            throw new DeliveryZoneAlreadyExistsException("Nome da zona de entrega");
        }
    }
}

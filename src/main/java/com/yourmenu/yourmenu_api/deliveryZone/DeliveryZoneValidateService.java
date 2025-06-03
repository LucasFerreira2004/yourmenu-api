package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneAlreadyExistsException;
import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeliveryZoneValidateService {
    
    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    public void existentDeliveryZone(DeliveryZone deliveryZone, String zoneName, Restaurant restaurant) {
        Optional<DeliveryZone> existingZone = deliveryZoneRepository.findByZoneAndRestaurant(zoneName, restaurant);

        if (existingZone.isPresent() && !existingZone.get().getId().equals(deliveryZone.getId())) {
            throw new DeliveryZoneAlreadyExistsException("Nome da zona de entrega");
        }
    }
}

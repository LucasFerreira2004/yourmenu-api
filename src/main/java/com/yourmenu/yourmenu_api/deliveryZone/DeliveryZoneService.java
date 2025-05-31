package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryZoneService {

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    private DeliveryZoneMapper deliveryZoneMapper;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantValidateService restaurantValidateService;

    @Autowired
    private DeliveryZoneValidateService deliveryZoneValidateService;

    public DeliveryZoneDto save(DeliveryZonePostDto deliveryZone, String adminId) {
        DeliveryZone entity = deliveryZoneMapper.toEntity(deliveryZone);
        
        // Busca o restaurante pelo slug e valida
        Restaurant restaurant = restaurantRepository.findBySlug(deliveryZone.restaurantSlug());
        restaurantValidateService.doAllValidations(restaurant, adminId, deliveryZone.restaurantSlug());

        deliveryZoneValidateService.existentDeliveryZone(entity, deliveryZone.zone(), restaurant);

        entity.setRestaurant(restaurant);
        return deliveryZoneMapper.toDto(deliveryZoneRepository.save(entity));
    }

    public List<DeliveryZoneDto> findAll(String restaurantId, String adminId) {
        deliveryZoneValidateService.validateRestaurantOwnerShip(restaurantId, adminId);
        return deliveryZoneMapper.toListDto(
                                    deliveryZoneRepository.findAllByRestaurantId(restaurantId));
    }

    public DeliveryZoneDto findById(Long id) {
        return deliveryZoneMapper.toDto(
                deliveryZoneRepository
                        .findById(id)
                        .orElseThrow(() -> new DeliveryZoneNotFoundException("id")));
    }

    public DeliveryZoneDto update(Long id, DeliveryZonePostDto deliveryZone, String adminId) {
        deliveryZoneValidateService.validateRestaurantOwnerShipPerDeliveryZone(id, adminId);
        DeliveryZone existingZone = deliveryZoneRepository
                .findById(id)
                .orElseThrow(() -> new DeliveryZoneNotFoundException("id"));

        // Busca o restaurante pelo slug e valida o existente
        Restaurant restaurant = restaurantRepository.findBySlug(deliveryZone.restaurantSlug());
        restaurantValidateService.doAllValidations(
                restaurant,
                existingZone.getRestaurant().getAdministrator().getId(),
                deliveryZone.restaurantSlug());

        deliveryZoneValidateService.existentDeliveryZone(existingZone, deliveryZone.zone(), restaurant);

        existingZone.setZone(deliveryZone.zone());
        existingZone.setDeliveryFee(deliveryZone.deliveryFee());
        existingZone.setRestaurant(restaurant);

        return deliveryZoneMapper.toDto(deliveryZoneRepository.save(existingZone));
    }

    public void delete(Long id, String adminId) {
        deliveryZoneValidateService.validateRestaurantOwnerShipPerDeliveryZone(id, adminId);
        deliveryZoneRepository.deleteById(id);
    }
}
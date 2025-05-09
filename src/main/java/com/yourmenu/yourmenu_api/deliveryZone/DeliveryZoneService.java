package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
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

    public DeliveryZoneDto save(DeliveryZonePostDto deliveryZone, String adminId) {
        DeliveryZone entity = deliveryZoneMapper.toEntity(deliveryZone);
        
        // Busca o restaurante pelo slug
        Restaurant restaurant = restaurantRepository.findBySlug(deliveryZone.restaurantSlug());
        
        // Valida se o restaurante existe e se o administrador tem permissão
        restaurantValidateService.doAllValidations(restaurant, adminId, deliveryZone.restaurantSlug());
        
        entity.setRestaurant(restaurant);
        return deliveryZoneMapper.toDto(deliveryZoneRepository.save(entity));
    }

    public List<DeliveryZoneDto> findAll() {
        return deliveryZoneMapper.toPageDto(deliveryZoneRepository.findAll());
    }

    public DeliveryZoneDto findById(Long id) {
        return deliveryZoneMapper.toDto(deliveryZoneRepository.findById(id).orElse(null));
    }

    public DeliveryZoneDto update(Long id, DeliveryZonePostDto deliveryZone) {
        DeliveryZone existingZone = deliveryZoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zona de entrega não encontrada"));

        // Busca o restaurante pelo slug
        Restaurant restaurant = restaurantRepository.findBySlug(deliveryZone.restaurantSlug());
        
        // Valida se o restaurante existe e se o administrador tem permissão
        restaurantValidateService.doAllValidations(restaurant, existingZone.getRestaurant().getAdministrator().getId(), 
            deliveryZone.restaurantSlug());

        existingZone.setZone(deliveryZone.zone());
        existingZone.setDeliveryFee(deliveryZone.deliveryFee());
        existingZone.setRestaurant(restaurant);

        return deliveryZoneMapper.toDto(deliveryZoneRepository.save(existingZone));
    }

    public void delete(Long id) {
        deliveryZoneRepository.deleteById(id);
    }
}
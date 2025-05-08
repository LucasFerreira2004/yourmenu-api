package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
import com.yourmenu.yourmenu_api.restaurant.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryZoneService {

    @Autowired
    private DeliveryZoneRepository deliveryZoneRepository;

    @Autowired
    private DeliveryZoneMapper deliveryZoneMapper;

    @Autowired
    private RestaurantService restaurantService;

    public DeliveryZoneDto save(DeliveryZonePostDto deliveryZone) {
        DeliveryZone entity = deliveryZoneMapper.toEntity(deliveryZone);
        return deliveryZoneMapper.toDto(deliveryZoneRepository.save(entity));
    }

    public List<DeliveryZoneDto> findAll() {
        return deliveryZoneMapper.toPageDto(deliveryZoneRepository.findAll());
    }

    public DeliveryZoneDto findById(Long id) {
        return deliveryZoneMapper.toDto(deliveryZoneRepository.findById(id).orElse(null));
    }

    public void delete(Long id) {
        deliveryZoneRepository.deleteById(id);
    }
}
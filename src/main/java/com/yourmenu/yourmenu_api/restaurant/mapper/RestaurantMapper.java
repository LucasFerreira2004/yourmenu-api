package com.yourmenu.yourmenu_api.restaurant.mapper;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO(
                restaurant.getId(),
                restaurant.getSlug(),
                restaurant.getName(),
                restaurant.getDeliveryTimeMin(),
                restaurant.getDeliveryTimeMax(),
                restaurant.getIsOpen(),
                restaurant.getProfilePicUrl(),
                restaurant.getBannerPicUrl()
        );
        return dto;
    }

    public Restaurant toEntity(RestaurantSaveDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(dto.name());
        restaurant.setDeliveryTimeMin(dto.deliveryTimeMin());
        restaurant.setDeliveryTimeMax(dto.deliveryTimeMax());
        return restaurant;
    }
}

package com.yourmenu.yourmenu_api.restaurant.mapper;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;

public class RestaurantMapper {
    public RestaurantDTO toDTO(Restaurant restaurant) {
        RestaurantDTO dto = new RestaurantDTO(
                restaurant.getSlug(),

        );
    }
}

package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transient
    public Restaurant saveRestaurant(RestaurantSaveDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurantRepository.save(restaurant);
        return RestaurantDTO(null);
    }
}

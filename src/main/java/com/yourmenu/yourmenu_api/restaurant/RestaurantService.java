package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.mapper.RestaurantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Transient
    public Restaurant saveRestaurant(RestaurantSaveDTO dto) {
        Restaurant restaurant = new Restaurant();
        restaurantRepository.save(restaurant);
        return
    }
}

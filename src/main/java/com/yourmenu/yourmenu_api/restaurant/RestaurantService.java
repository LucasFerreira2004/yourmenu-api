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
    public RestaurantDTO save(RestaurantSaveDTO dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurant.setIsOpen(false);
        restaurant.setSlug("restaurant001");
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }
}

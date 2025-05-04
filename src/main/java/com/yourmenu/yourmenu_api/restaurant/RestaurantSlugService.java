package com.yourmenu.yourmenu_api.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantSlugService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public String generateSlug(String restaurantName) {

    }
    public boolean isFree(String slug) {
        return restaurantRepository.findBySlug(slug) == null;
    }

    public String slugFormater(String restaurantName) {
        restaurantName = restaurantName.toLowerCase();
        restaurantName = restaurantName.replaceAll(" ", "-");
        return restaurantName;
    }
}

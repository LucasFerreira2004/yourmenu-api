package com.yourmenu.yourmenu_api.restaurantAdress;

import com.yourmenu.yourmenu_api.globalExceptions.DeniedAccessException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantAdressValidateService {
    @Autowired
    private RestaurantAdressRepository restaurantAdressRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validateRestaurantOwnership(String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        if (!restaurant.getAdministrator().getId().equals(adminId)) throw new DeniedAccessException("AdminId");
    }

    public void validateRestaurantExists(String restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

    public void validateAllToSave(String restaurantId, String adminId){
        validateRestaurantOwnership(restaurantId, adminId);
    }
}

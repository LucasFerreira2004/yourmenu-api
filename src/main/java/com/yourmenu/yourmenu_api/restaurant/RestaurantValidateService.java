package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.shared.globalExceptions.DeniedAccessException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.UserNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ExistentResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantValidateService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validateAdministratorCanEditOrViewRestaurant(String restaurantId, String administratorId) {
        Restaurant restaurant = validateRestaurantExists(restaurantId);
        if (administratorId == null) {
            throw new UserNotFoundException("administratorId");
        }
        if(!restaurant.getAdministrator().getId().equals(administratorId)) {
            throw new DeniedAccessException("administratorId", "Você não tem permissão para acessar ou modificar este restaurante");
        }
    }
    public void validateRestaurantExists(Restaurant restaurant) {
        if (restaurant == null) throw new RestaurantNotFoundException();
    }
    public Restaurant validateRestaurantExists(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if (restaurant == null) throw new RestaurantNotFoundException();
        return restaurant;
    }

    public void validateAllToUpdate(Restaurant restaurant, String administratorId) {
        validateRestaurantExists(restaurant);
        validateAdministratorCanEditOrViewRestaurant(restaurant.getId(), administratorId);
    }

    public void validateAllToSave(String administratorId) {
        Restaurant restaurnatResponse = restaurantRepository.findByAdministratorId(administratorId);
        if (restaurnatResponse != null) throw new ExistentResourceException("restaurant");
    }
}
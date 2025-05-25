package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.shared.globalExceptions.DeniedAccessException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.UserNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantValidateService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validateAdministratorCanEditRestaurant(Restaurant restaurant, String administratorId) {
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

    public void validateAllToSave(Restaurant restaurant, String administratorId) {
        validateRestaurantExists(restaurant);
        validateAdministratorCanEditRestaurant(restaurant, administratorId);
    }
}

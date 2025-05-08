package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.globalExceptions.DeniedAccessException;
import com.yourmenu.yourmenu_api.globalExceptions.UserNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantValidateService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void authorizeAdministratorAccess(Restaurant restaurant, String administratorId) {
        if (administratorId == null) {
            throw new UserNotFoundException("administratorId");
        }
        if(!restaurant.getAdministrator().getId().equals(administratorId)) {
            throw new DeniedAccessException("administratorId", "Você não tem permissão para modificar este restaurante");
        }
    }
    public void existentRestaurant(Restaurant restaurant, String errorField) {
        if (restaurant == null) throw new RestaurantNotFoundException(errorField);
    }

    public void validateToUpdate(Restaurant restaurant, String administratorId, String errorField) {
        existentRestaurant(restaurant, errorField);
        authorizeAdministratorAccess(restaurant, administratorId);
    }
}

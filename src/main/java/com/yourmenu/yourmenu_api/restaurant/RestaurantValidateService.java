package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.AdministratorRepository;
import com.yourmenu.yourmenu_api.globalExceptions.UserNotFoundException;
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

    }
}

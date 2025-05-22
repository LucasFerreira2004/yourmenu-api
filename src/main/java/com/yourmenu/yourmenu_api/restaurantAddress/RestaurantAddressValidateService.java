package com.yourmenu.yourmenu_api.restaurantAddress;

import com.yourmenu.yourmenu_api.shared.globalExceptions.DeniedAccessException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.restaurantAddress.exceptions.RestaurantAddressNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantAddressValidateService {
    @Autowired
    private RestaurantAdressRepository restaurantAdressRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void validateRestaurantOwnership(String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException("restaurantId"));
        if (!restaurant.getAdministrator().getId().equals(adminId)) throw new DeniedAccessException("AdminId");
    }

    public void validateAllToSave(String restaurantId, String adminId){
        validateRestaurantOwnership(restaurantId, adminId);
    }

    public void validateAdressExists(String restaurantId){
        RestaurantAddress adress = restaurantAdressRepository.findByRestaurantId(restaurantId);
        if (adress == null) throw new RestaurantAddressNotFoundException("restaurantId");
    }

    public void validateAllToUpdate(String restaurantId, String adminId) {
        validateAdressExists(restaurantId);
        validateAllToSave(restaurantId, adminId);
    }
}

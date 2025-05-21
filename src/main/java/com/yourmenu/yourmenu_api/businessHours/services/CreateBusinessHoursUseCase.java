package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.Weekday;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBusinessHoursUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public void execute(Restaurant restaurant) {

        this.restaurantRepository.findById(restaurant.getId())
                .orElseThrow(() -> new RestaurantNotFoundException("id_restaurant"));

        for (int i = 0; i < Weekday.values().length; i++) {
            BusinessHours businessHours = new BusinessHours();
            businessHours.setRestaurant(restaurant);
            businessHours.setWeekday(Weekday.values()[i]);
            businessHoursRepository.save(businessHours);
        }
    }
}
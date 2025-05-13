package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.Weekday;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBusinessHoursUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    public void execute(Restaurant restaurant) {
        for (int i = 0; i < Weekday.values().length; i++) {
            BusinessHours businessHours = new BusinessHours();
            businessHours.setRestaurant(restaurant);
            businessHours.setWeekday(Weekday.values()[i]);
            businessHoursRepository.save(businessHours);
        }
    }
}
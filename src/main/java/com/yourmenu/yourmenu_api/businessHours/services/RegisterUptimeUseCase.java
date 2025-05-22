package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.Weekday;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursPeriodDTO;
import com.yourmenu.yourmenu_api.businessHours.exception.BusinessHoursInvalidException;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class RegisterUptimeUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public void execute(String id_restaurant, BusinessHoursPeriodDTO dto) {

        this.restaurantRepository.findById(id_restaurant)
                .orElseThrow(() -> new RestaurantNotFoundException("id_restaurant"));

        if (dto.openingTime().equals(dto.closingTime())) {
            throw new BusinessHoursInvalidException("Horário de abertura deve ser antes do horário de fechamento");
        }

        for(int i = 0; i < Weekday.values().length; i++){
            saveNewTime(id_restaurant, Weekday.values()[i], null, null);
        }

        int start = dto.weekday_start().ordinal();
        int end = dto.weekday_end().ordinal();

        if(start == end){
            saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
        }else if(start > end){
            while(start != end){
                if(start == 6){
                    saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
                    start = 0;
                    continue;
                }
                saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
                start++;
            }
            saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
        }else{
            while(start != end){
                saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
                start++;
            }
            saveNewTime(id_restaurant, Weekday.values()[start], dto.openingTime(), dto.closingTime());
        }
    }

    private BusinessHours findBusinessHoursByRestaurantIdAndWeekday(String id_restaurant, Weekday weekday) {
        BusinessHours businessHoursOpt = businessHoursRepository
                .findByRestaurantIdAndWeekday(id_restaurant, weekday);
        return businessHoursOpt;
    }

    private void saveNewTime(String id_restaurant, Weekday weekday, LocalTime openingTime, LocalTime closingTime) {
        BusinessHours businessHourSet;
        businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, weekday);
        businessHourSet.setOpeningTime(openingTime);
        businessHourSet.setClosingTime(closingTime);
        businessHoursRepository.save(businessHourSet);
    }
}

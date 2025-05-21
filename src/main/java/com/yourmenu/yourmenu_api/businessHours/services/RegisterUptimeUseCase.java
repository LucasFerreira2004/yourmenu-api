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

@Service
public class RegisterUptimeUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    public void execute(String id_restaurant, BusinessHoursPeriodDTO dto) {

        this.restaurantRepository.findById(id_restaurant)
                .orElseThrow(() -> new RestaurantNotFoundException("id_restaurant"));

        if (dto.openingTime().isAfter(dto.closingTime()) || dto.openingTime().equals(dto.closingTime())) {
            throw new BusinessHoursInvalidException("Horário de abertura deve ser antes do horário de fechamento");
        }

        BusinessHours businessHourSet;

        for(int i = 0; i < Weekday.values().length; i++){
            businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[i]);
            businessHourSet.setOpeningTime(null);
            businessHourSet.setClosingTime(null);
            businessHoursRepository.save(businessHourSet);
        }

        int start = dto.weekday_start().ordinal();
        int end = dto.weekday_end().ordinal();

        if(start == end){
            businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
            businessHourSet.setOpeningTime(dto.openingTime());
            businessHourSet.setClosingTime(dto.closingTime());
            businessHoursRepository.save(businessHourSet);

        }else if(start > end){
            while(start != end){
                if(start == 6){
                    businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
                    businessHourSet.setOpeningTime(dto.openingTime());
                    businessHourSet.setClosingTime(dto.closingTime());
                    businessHoursRepository.save(businessHourSet);
                    start = 0;
                    continue;
                }
                businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
                businessHourSet.setOpeningTime(dto.openingTime());
                businessHourSet.setClosingTime(dto.closingTime());
                businessHoursRepository.save(businessHourSet);
                start++;
            }
            businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
            businessHourSet.setOpeningTime(dto.openingTime());
            businessHourSet.setClosingTime(dto.closingTime());
            businessHoursRepository.save(businessHourSet);
        }else{
            while(start != end){
                businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
                businessHourSet.setOpeningTime(dto.openingTime());
                businessHourSet.setClosingTime(dto.closingTime());
                businessHoursRepository.save(businessHourSet);
                start++;
            }
            businessHourSet = findBusinessHoursByRestaurantIdAndWeekday(id_restaurant, Weekday.values()[start]);
            businessHourSet.setOpeningTime(dto.openingTime());
            businessHourSet.setClosingTime(dto.closingTime());
            businessHoursRepository.save(businessHourSet);
        }
    }

    //lançar exception
    private BusinessHours findBusinessHoursByRestaurantIdAndWeekday(String id_restaurant, Weekday weekday) {
        BusinessHours businessHoursOpt = businessHoursRepository
                .findByRestaurantIdAndWeekday(id_restaurant, weekday);
        return businessHoursOpt;
    }
}

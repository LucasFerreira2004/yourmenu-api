package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.Weekday;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursPeriodDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterUptimeUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    public void execute(String id_restaurant, BusinessHoursPeriodDTO dto) {

        int start = dto.weekday_start().ordinal();
        int end = dto.weekday_end().ordinal();

        System.out.println("valores iniciais: " + start + " - " + end);

        if(start == end){
            System.out.println("abre as: " + Weekday.values()[start]);
        }else if(start > end){
            while(start != end){
                if(start == 6){
                    System.out.println("abre as: " + Weekday.values()[start]);
                    start = 0;
                    continue;
                }
                System.out.println("abre as: " + Weekday.values()[start]);
                start++;
            }
            System.out.println("abre as: " + Weekday.values()[start]);
        }else{
            while(start != end){
                System.out.println("abre as: " + Weekday.values()[start]);
                start++;
            }
            System.out.println("abre as: " + Weekday.values()[start]);
        }
        System.out.println("----------------------------");
    }
}

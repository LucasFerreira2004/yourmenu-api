package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.businessHours.mapper.BusinessHoursMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingBusinessHoursUseCase {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    BusinessHoursMapper businessHoursMapper;

    public List<BusinessHoursDTO> execute(String restaurantId) {
        List<BusinessHours> businessHoursList = businessHoursRepository.findAllByRestaurantIdOrderByWeekday(restaurantId);

        return businessHoursList.stream()
                .map(businessHoursMapper::toDTO)
                .collect(Collectors.toList());
    }
}
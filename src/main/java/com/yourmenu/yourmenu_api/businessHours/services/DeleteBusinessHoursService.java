package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteBusinessHoursService {

    private final BusinessHoursRepository businessHoursRepository;

    public void deleteBusinessHours(String id_restaurant) {
        businessHoursRepository.deleteByRestaurantId(id_restaurant);
    }
}

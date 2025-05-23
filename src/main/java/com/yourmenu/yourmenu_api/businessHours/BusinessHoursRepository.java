package com.yourmenu.yourmenu_api.businessHours;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, UUID> {
    List<BusinessHours> findAllByRestaurantId(String restaurantId);
    BusinessHours findByRestaurantIdAndWeekday(String restaurantId, Weekday weekday);
}

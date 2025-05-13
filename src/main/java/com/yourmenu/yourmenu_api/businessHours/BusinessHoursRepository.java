package com.yourmenu.yourmenu_api.businessHours;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;

public interface BusinessHoursRepository extends JpaRepository<BusinessHours, UUID> {
    List<BusinessHours> findAllByRestaurantIdOrderByWeekday(String restaurantId);
}

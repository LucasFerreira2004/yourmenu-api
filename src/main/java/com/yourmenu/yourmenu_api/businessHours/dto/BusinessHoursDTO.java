package com.yourmenu.yourmenu_api.businessHours.dto;

import com.yourmenu.yourmenu_api.businessHours.Weekday;

import java.time.LocalTime;
import java.util.UUID;

public record BusinessHoursDTO(
        UUID id_businessHours,
        Weekday weekday,
        LocalTime openingTime,
        LocalTime closingTime
) {
}

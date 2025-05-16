package com.yourmenu.yourmenu_api.businessHours.dto;

import com.yourmenu.yourmenu_api.businessHours.Weekday;

import java.time.LocalTime;

public record BusinessHoursPeriodDTO(
        Weekday weekday_start,
        Weekday weekday_end,
        LocalTime openingTime,
        LocalTime closingTime
) {
}

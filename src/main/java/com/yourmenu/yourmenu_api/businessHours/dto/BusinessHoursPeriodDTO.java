package com.yourmenu.yourmenu_api.businessHours.dto;

import com.yourmenu.yourmenu_api.businessHours.Weekday;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record BusinessHoursPeriodDTO(
        @NotNull(message = "Dia de início não pode ser nulo")
        Weekday weekday_start,
        @NotNull(message = "Dia de fim não pode ser nulo")
        Weekday weekday_end,
        @NotNull(message = "Horário de abertura não pode ser nula")
        LocalTime openingTime,
        @NotNull(message = "Horário de fechamento não pode ser nulo")
        LocalTime closingTime
) {
}

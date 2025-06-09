package com.yourmenu.yourmenu_api.deliveryZone.dto;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Schema(name = "Delivery_Zone")
public record DeliveryZonePostDto(
        @NotBlank(message = "A zona é obrigatória")
        String zone,

        @NotNull(message = "O valor da entrega é obrigatório")
        @Min(message = "O valor da entrega deve ser maior que zero", value = 0)
        BigDecimal deliveryFee,

        @NotBlank(message = "O slug do restaurante é obrigatório")
        String restaurantSlug
) {
}

package com.yourmenu.yourmenu_api.restaurant.dto;

public record RestaurantDTO(
        String slug,
        String name,
        Integer deliveryTimeMin,
        Integer deliveryTimeMax,
        Boolean isOpen,
        String profilePicUrl,
        String BannerPicUrl
) {
}

package com.yourmenu.yourmenu_api.restaurant.dto;

public record RestaurantDTO(
        String slug,
        String name,
        String deliveryTimeMin,
        String deliveryTimeMax,
        Boolean isOpen,
        String profilePicUrl,
        String BannerPicUrl
) {
}

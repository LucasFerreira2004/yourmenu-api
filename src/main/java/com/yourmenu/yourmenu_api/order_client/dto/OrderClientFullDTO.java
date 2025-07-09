package com.yourmenu.yourmenu_api.order_client.dto;

public record OrderClientFullDTO(
        Long id,
        Long orderId,
        String firstName,
        String lastName,
        Long phone
) {}

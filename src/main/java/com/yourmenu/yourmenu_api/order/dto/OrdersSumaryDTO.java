package com.yourmenu.yourmenu_api.order.dto;

import java.math.BigDecimal;

public record OrdersSumaryDTO(
        Long totalOrdersSold,
        BigDecimal revenue
) {
}

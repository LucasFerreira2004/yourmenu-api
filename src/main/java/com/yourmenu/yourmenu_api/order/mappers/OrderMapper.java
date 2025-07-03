package com.yourmenu.yourmenu_api.order.mappers;
import com.yourmenu.yourmenu_api.order.Order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;

public class OrderMapper {

    public static Order toEntity(OrderSaveDTO dto, Restaurant restaurant) {
        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setDateTime(dto.dateTime());
        order.setPrice(null);
        order.setStatus(dto.status());
        order.setNote(order.getNote());
        return order;
    }

    public static OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getRestaurant().getId(),
                order.getDateTime(),
                null,
                order.getStatus(),
                order.getNote(),
                null
        );
    }
}

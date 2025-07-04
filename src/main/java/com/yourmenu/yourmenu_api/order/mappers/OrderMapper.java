package com.yourmenu.yourmenu_api.order.mappers;
import com.yourmenu.yourmenu_api.order.Order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import com.yourmenu.yourmenu_api.orderItem.mapper.OrderItemMapper;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMapper {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    public Order toEntity(OrderSaveDTO dto, Restaurant restaurant) {
        BigDecimal price = orderItemService.getTotalPriceByList(dto.orderItems());
        Order order = new Order();
        order.setRestaurant(restaurant);
        order.setDateTime(dto.dateTime());
        order.setPrice(price);
        order.setStatus(dto.status());
        order.setNote(order.getNote());
        return order;
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getRestaurant().getId(),
                order.getDateTime(),
                order.getPrice(),
                order.getStatus(),
                order.getNote(),
                order.getOrderItems().stream().map(x-> orderItemMapper.toDTO(x)).toList()
        );
    }
}

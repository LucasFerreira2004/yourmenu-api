package com.yourmenu.yourmenu_api.order.mappers;
import com.yourmenu.yourmenu_api.order.Order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderMinDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.orderAdress.OrderAdressMapper;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import com.yourmenu.yourmenu_api.orderItem.mapper.OrderItemMapper;
import com.yourmenu.yourmenu_api.order_client.OrderClientMapper;
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

    @Autowired
    private OrderAdressMapper orderAdressMapper;

    @Autowired
    private OrderClientMapper orderClientMapper;

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
                order.getOrderItems().stream().map(x -> orderItemMapper.toDTO(x)).toList(),
                orderAdressMapper.toDTO(order.getOrderAdress()),
                orderClientMapper.toDTO(order.getOrderClient())
        );
    }

    public OrderMinDTO toMinDTO(Order order){
        return new OrderMinDTO(
                order.getId(),
                order.getStatus(),
                order.getOrderItems().stream().map(x -> orderItemMapper.toDTO(x)).toList(),
                orderAdressMapper.toDTO(order.getOrderAdress()),
                order.getPrice()
                );
    }
}

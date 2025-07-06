package com.yourmenu.yourmenu_api.orderItem.mapper;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.mappers.SizeOptionsMapper;
import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.orderItem.OrderItem;
import com.yourmenu.yourmenu_api.orderItem.OrderItemRepository;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemDTO;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderItemMapper {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishSizeOptionRepository dishSizeOptionRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private SizeOptionsMapper sizeOptionsMapper;

    public  OrderItem toEntity(OrderItemSaveDTO dto, Long orderId) {
        OrderItem orderItem = new OrderItem();
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order"));
        DishSizeOption dishSizeOption = dishSizeOptionRepository.findById(dto.dishSizeOptionId()).orElseThrow(() -> new ResourceNotFoundException("DishSizeOption"));
        BigDecimal itemPrice = orderItemRepository.findPriceByDishSizeOptionId(dto.quantity(), dto.dishSizeOptionId());
        orderItem.setQuantity(dto.quantity());
        orderItem.setOrder(order);
        orderItem.setDishSizeOption(dishSizeOption);
        orderItem.setPrice(itemPrice);

        return orderItem;
    }

    public OrderItemDTO toDTO(OrderItem orderItem) {
        return new OrderItemDTO(
                orderItem.getId(),
                orderItem.getDishSizeOption().getId(),
                orderItem.getDishSizeOption().getDish().getName(),
                sizeOptionsMapper.toDTO(orderItem.getDishSizeOption().getSizeOption()),
                orderItem.getQuantity(),
                orderItem.getPrice()
        );
    }
}

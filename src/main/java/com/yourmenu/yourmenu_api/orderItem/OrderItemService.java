package com.yourmenu.yourmenu_api.orderItem;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import com.yourmenu.yourmenu_api.orderItem.mapper.OrderItemMapper;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    public OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private DishSizeOptionRepository dishSizeOptionRepository;

    public List<OrderItem> saveOrderItems(List<OrderItemSaveDTO> itemsDtos, Long orderId) {
        for (OrderItemSaveDTO orderItemSaveDTO : itemsDtos) {
            OrderItem orderItem = orderItemMapper.toEntity(orderItemSaveDTO, orderId);
            orderItemRepository.save(orderItem);
        }
        return orderItemRepository.findAllByOrderId(orderId);
    }

    public BigDecimal getTotalPriceByList(List<OrderItemSaveDTO> items) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderItemSaveDTO item : items) {
            DishSizeOption dishSizeOption = dishSizeOptionRepository.findById(item.dishSizeOptionId()).orElseThrow();
            totalPrice = totalPrice.add(dishSizeOption.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
        }
        return totalPrice;
    }
}

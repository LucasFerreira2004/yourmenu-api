package com.yourmenu.yourmenu_api.order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.orderItem.OrderItem;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCase {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    //order address e order client

    @Transactional
    public OrderDTO execute(OrderSaveDTO saveDTO, String restaurantId) {
        Order order =  orderService.saveOrder(saveDTO, restaurantId);
        List<OrderItem> items =  orderItemService.saveOrderItems(saveDTO.orderItems(), order.getId());
        order.setOrderItems(items);
        return OrderMapper.toDTO(order);
    }
}

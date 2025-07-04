package com.yourmenu.yourmenu_api.order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderUseCase {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    //order address e order client

    public OrderDTO createOrder(OrderSaveDTO saveDTO, String restaurantId) {
        Order order =  orderService.saveOrder(saveDTO, restaurantId);
        orderItemService.saveOrderItems(saveDTO.orderItems(), order.getId());
        order = orderRepository.findById(order.getId()).orElseThrow();
        return OrderMapper.toDTO(order);
    }
}

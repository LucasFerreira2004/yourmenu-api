package com.yourmenu.yourmenu_api.order.services;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAllByRestaurant(String restaurantId) {
            List<Order> orders = orderRepository.findAllByRestaurantIdOrderByDate(restaurantId);
            return orders.stream().map(x -> OrderMapper.toDTO(x)).toList();
    }
}

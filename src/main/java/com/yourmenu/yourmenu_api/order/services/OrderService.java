package com.yourmenu.yourmenu_api.order.services;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getAllByRestaurant(String restaurantId) {
        List<Order> orders = orderRepository.findAllByRestaurantIdOrderByDateTime(restaurantId);
        return orders.stream().map(x -> OrderMapper.toDTO(x)).toList();
    }

    public List<OrderDTO> getAllByRestaurantAndDate(String restaurantId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        List<Order> orders = orderRepository.findAllByRestaurantAndDateTime(restaurantId, startOfDay);
        return orders.stream().map(x -> OrderMapper.toDTO(x)).toList();
    }
}

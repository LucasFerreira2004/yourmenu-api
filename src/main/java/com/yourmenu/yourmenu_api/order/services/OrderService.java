package com.yourmenu.yourmenu_api.order.services;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.OrderStatus;
import com.yourmenu.yourmenu_api.order.dto.OrderByStatusDTO;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.EntityDoesNotBelongToAnotherEntityException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Order> orders = orderRepository.findAllByRestaurantAndDate(restaurantId, startOfDay, endOfDay);
        return orders.stream().map(x -> OrderMapper.toDTO(x)).toList();
    }

    public OrderDTO getById(String restaurantId, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RestaurantNotFoundException("Order"));
        if (!order.getRestaurant().getId().equals(restaurantId))
            throw new EntityDoesNotBelongToAnotherEntityException("Order", "Restaurant");
        return OrderMapper.toDTO(order);
    }

    public List<OrderByStatusDTO> getAlByRestaurantDateAndStatus(String restaurantId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Order> orders = orderRepository.findAllByRestaurantAndDate(restaurantId, startOfDay, endOfDay);

        Map<OrderStatus, List<Order>> ordersGrouped = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        List<OrderByStatusDTO> ordersByStatus = new ArrayList<>();
        for (OrderStatus status : ordersGrouped.keySet()) {
            ordersByStatus.add(new OrderByStatusDTO(status, ordersGrouped.get(status).stream().map(OrderMapper::toDTO).toList()));
        }
        return ordersByStatus;
    }
}

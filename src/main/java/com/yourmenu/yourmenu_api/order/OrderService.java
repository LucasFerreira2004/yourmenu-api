package com.yourmenu.yourmenu_api.order;

import com.yourmenu.yourmenu_api.order.dto.*;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.order.validation.OrderValidateService;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.EntityDoesNotBelongToAnotherEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.math.BigDecimal;
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

    @Autowired
    private RestaurantValidateService restaurantValidateService;
    
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderValidateService orderValidateService;

    public Order saveOrder(OrderSaveDTO saveDTO, String restaurantId) {
        Restaurant restaurant = restaurantRepository.findByid(restaurantId);
        if (restaurant == null) throw new ResourceNotFoundException("Restaurant");
        orderValidateService.validateToSave(saveDTO);
        Order order = orderMapper.toEntity(saveDTO, restaurant);
        orderRepository.save(order);
        return order;
    }

    public List<OrderMinDTO> getAllByRestaurant(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findByid(restaurantId);
        if (restaurant == null) throw new ResourceNotFoundException("Restaurant");
        List<Order> orders = orderRepository.findAllByRestaurantIdOrderByDateTime(restaurantId);
        return orders.stream().map(x -> orderMapper.toMinDTO(x)).toList();
    }

    public List<OrderMinDTO> getAllByRestaurantAndDate(String restaurantId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Order> orders = orderRepository.findAllByRestaurantAndDate(restaurantId, startOfDay, endOfDay);
        return orders.stream().map(x -> orderMapper.toMinDTO(x)).toList();
    }

    public OrderDTO getById(String restaurantId, Long orderId) {
        Restaurant restaurant = restaurantRepository.findByid(restaurantId);
        if (restaurant == null) throw new ResourceNotFoundException("Restaurant");
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order"));
        if (!order.getRestaurant().getId().equals(restaurantId))
            throw new EntityDoesNotBelongToAnotherEntityException("Order", "Restaurant");
        return orderMapper.toDTO(order);
    }

    public List<OrderByStatusDTO> getAlByRestaurantDateAndStatus(String restaurantId, LocalDate date){
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        List<Order> orders = orderRepository.findAllByRestaurantAndDate(restaurantId, startOfDay, endOfDay);

        Map<OrderStatus, List<Order>> ordersGrouped = orders.stream()
                .collect(Collectors.groupingBy(Order::getStatus));
        List<OrderByStatusDTO> ordersByStatus = new ArrayList<>();
        for (OrderStatus status : ordersGrouped.keySet()) {
            ordersByStatus.add(new OrderByStatusDTO(status, ordersGrouped.get(status).stream().map(orderMapper::toMinDTO).toList()));
        }
        return ordersByStatus;
    }

    public OrderDTO updateStatus(String restaurantId, Long orderId, UpdateOrderStatusDTO statusDTO) {
        Restaurant restaurant = restaurantRepository.findByid(restaurantId);
        if (restaurant == null) throw new ResourceNotFoundException("Restaurant");
        Order order = orderRepository.findByIdByRestaurant(orderId, restaurantId);
        if (order == null) {
            throw new ResourceNotFoundException("Id do pedido");
        }
        OrderStatus status = statusDTO.status();
        order.setStatus(status);
        orderRepository.save(order);
        return orderMapper.toDTO(order);
    }

    public OrdersSumaryDTO getSummaryByDate(String restaurantId, LocalDate date, String adminsitratorId) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
        restaurantValidateService.validateAdministratorCanEditOrViewRestaurant(restaurantId, adminsitratorId);
        BigDecimal revenueByDate = orderRepository.findRevenueByRestaurantAndDate(restaurantId, startOfDay, endOfDay);
        Long qtdOrdersByDate = orderRepository.findQtdOrdersByRestaurantAndDate(restaurantId, startOfDay, endOfDay);

        return new OrdersSumaryDTO(qtdOrdersByDate, revenueByDate);
    }
}

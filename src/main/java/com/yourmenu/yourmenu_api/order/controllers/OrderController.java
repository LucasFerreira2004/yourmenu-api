package com.yourmenu.yourmenu_api.order.controllers;

import com.yourmenu.yourmenu_api.order.OrderStatus;
import com.yourmenu.yourmenu_api.order.dto.OrderByStatusDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllByRestaurant(@PathVariable String restaurantId){
        List<OrderDTO> orders = orderService.getAllByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<OrderDTO>> getAllByRestaurantAndDate(@PathVariable String restaurantId, @RequestParam LocalDate date){
        List<OrderDTO> orders = orderService.getAllByRestaurantAndDate(restaurantId, date);
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getById(@PathVariable String restaurantId, @PathVariable Long orderId){
        OrderDTO order = orderService.getById(restaurantId, orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/status/by-date")
    public ResponseEntity<List<OrderByStatusDTO>> getAllByRestaurantDateAndStatus(@PathVariable String restaurantId, @RequestParam LocalDate date){
        List<OrderByStatusDTO> orders = orderService.getAlByRestaurantDateAndStatus(restaurantId, date);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateStatus(
            @PathVariable(value = "orderId") Long orderId,
            @RequestParam(value = "restaurantId") String restaurantId,
            @RequestParam OrderStatus status) {
        OrderDTO order = orderService.updateStatus(restaurantId, orderId, status);
        return ResponseEntity.ok(order);
    }
}

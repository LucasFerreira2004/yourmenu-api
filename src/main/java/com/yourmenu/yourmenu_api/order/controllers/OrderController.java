package com.yourmenu.yourmenu_api.order.controllers;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

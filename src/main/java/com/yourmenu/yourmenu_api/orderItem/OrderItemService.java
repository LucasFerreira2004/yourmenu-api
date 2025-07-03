package com.yourmenu.yourmenu_api.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OrderItemService {
    @Autowired
    public OrderItemRepository orderItemRepository;

    public BigDecimal findOrderItemPrice(Long id){
        return orderItemRepository.findPriceById(id);
    }
}

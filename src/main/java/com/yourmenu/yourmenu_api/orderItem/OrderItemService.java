package com.yourmenu.yourmenu_api.orderItem;

import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService {
    @Autowired
    public OrderItemRepository orderItemRepository;

   public void saveOrderItems(List<OrderItemSaveDTO> itemsDtos){
       BigDecimal itemPrice = BigDecimal.ZERO;
       for (OrderItemSaveDTO orderItemSaveDTO : itemsDtos) {

           itemPrice = orderItemRepository.findPriceByDishSizeOptionId(orderItemSaveDTO.quantity(), orderItemSaveDTO.dishSizeOptionId());
       }

   }
}

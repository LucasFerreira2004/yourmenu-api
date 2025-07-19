package com.yourmenu.yourmenu_api.order.validation;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderValidateService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    RestaurantValidateService restaurantValidateService;

    @Autowired
    DishSizeOptionRepository dishSizeOptionRepository;



    public void validateToSave(OrderSaveDTO saveDTO) {
        validateToDishSizeOption(saveDTO.orderItems());
    }

    private void validateToDishSizeOption(List<OrderItemSaveDTO> orderItens) {
        for (OrderItemSaveDTO orderItemSaveDTO : orderItens) {
            Long dishSizeOptionId = orderItemSaveDTO.dishSizeOptionId();
            dishSizeOptionRepository.findById(dishSizeOptionId)
                    .orElseThrow(() -> new ResourceNotFoundException("dish_size_option: " + dishSizeOptionId + " not found"));
        }
    }

}

package com.yourmenu.yourmenu_api.order.validation;

import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZoneRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.order.exceptions.RestaurantNotOperationException;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressPostDto;
import com.yourmenu.yourmenu_api.orderItem.dto.OrderItemSaveDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientSaveDTO;
import com.yourmenu.yourmenu_api.order_client.exceptions.InvalidPhoneNumberException;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import com.yourmenu.yourmenu_api.shared.utils.PhoneValidator;
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

    @Autowired
    DeliveryZoneRepository deliveryZoneRepository;



    public void validateToSave(OrderSaveDTO saveDTO) {
        validateRestaurantOperation(saveDTO.restaurantId());
        validateToDishSizeOption(saveDTO.orderItems());
        validateToDeliveryZone(saveDTO.orderAdress());
        validationToPhone(saveDTO.orderClient());
    }

    private void validateToDishSizeOption(List<OrderItemSaveDTO> orderItens) {
        for (OrderItemSaveDTO orderItemSaveDTO : orderItens) {
            Long dishSizeOptionId = orderItemSaveDTO.dishSizeOptionId();
            dishSizeOptionRepository.findById(dishSizeOptionId)
                    .orElseThrow(() -> new ResourceNotFoundException("dish_size_option: " + dishSizeOptionId + " not found"));
        }
    }

    private void validateToDeliveryZone(OrderAdressPostDto orderAdress){
        Long deliveryZoneId = orderAdress.deliveryZoneId();
        deliveryZoneRepository.findById(deliveryZoneId)
                .orElseThrow(() -> new ResourceNotFoundException("delivery_zone: " + deliveryZoneId + " not found"));
    }

    private void validationToPhone(OrderClientSaveDTO orderClient){
        if (!PhoneValidator.isValidPhoneNumber(orderClient.phone(), "BR")) {
            throw new InvalidPhoneNumberException();
        }

    }

    private void validateRestaurantOperation(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findByid(restaurantId);

        if(restaurant == null)
            throw new ResourceNotFoundException(
                    "RestaurantId",
                    "Restaurante nao encontrado como id " + restaurantId);

        if(!restaurant.getIsOpen())
            throw new RestaurantNotOperationException(
                    "RestaurantId",
                    "O Restaurante não esta aberto no momento para receber novos pedidos");
    }

}

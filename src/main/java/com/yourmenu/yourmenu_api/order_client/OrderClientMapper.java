package com.yourmenu.yourmenu_api.order_client;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientSaveDTO;
import org.springframework.stereotype.Component;

@Component
public class OrderClientMapper {

    public OrderClient toEntity(OrderClientSaveDTO dto, Order order) {
        OrderClient orderClient = new OrderClient();
        orderClient.setOrder(order);
        orderClient.setFirstName(dto.firstName());
        orderClient.setLastName(dto.lastName());
        orderClient.setPhone(dto.phone());
        return orderClient;
    }

    public OrderClientDTO toDTO(OrderClient entity) {
        return new OrderClientDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone()
        );
    }
}

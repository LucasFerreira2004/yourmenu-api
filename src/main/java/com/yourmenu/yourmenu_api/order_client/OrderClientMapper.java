package com.yourmenu.yourmenu_api.order_client;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientFullDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientSaveDTO;
import com.yourmenu.yourmenu_api.shared.utils.NameDivider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderClientMapper {



    public OrderClient toEntity(OrderClientSaveDTO dto, Order order) {
        String firstName = NameDivider.getFirstName(dto.name());
        String lastName = NameDivider.getLastname(dto.name());
        OrderClient orderClient = new OrderClient();
        orderClient.setOrder(order);
        orderClient.setFirstName(firstName);
        orderClient.setLastName(lastName);
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

    public OrderClientFullDTO toFullDTO(OrderClient entity) {
        return new OrderClientFullDTO(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getPhone()
        );
    }
}

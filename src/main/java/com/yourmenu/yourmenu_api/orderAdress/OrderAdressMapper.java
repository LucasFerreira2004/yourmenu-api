package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZoneMapper;
import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZoneRepository;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressPostDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", uses = { DeliveryZoneMapper.class})
@Component
public abstract class OrderAdressMapper {
    @Autowired
    public OrderRepository orderRepository;

    @Autowired
    public DeliveryZoneRepository deliveryZoneRepository;

    @Mapping(target = "deliveryZone", expression = "java( deliveryZoneRepository.findById(dto.deliveryZoneId()).orElse(null) )")
    @Mapping(target = "order", expression = "java( orderRepository.findById(orderId).orElse(null) )")
    public abstract OrderAdress toEntity(OrderAdressPostDto dto, Long orderId);

    public abstract OrderAdressDto toDTO(OrderAdress entity);
}

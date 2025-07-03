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

@RequiredArgsConstructor
@Mapper(componentModel = "spring", uses = { DeliveryZoneMapper.class, OrderMapper.class })
public abstract class OrderAdressMapper {

    private final OrderRepository orderRepository;
    private final DeliveryZoneRepository deliveryZoneRepository;

    @Mapping(target = "deliveryZone", expression = "java( deliveryZoneRepository.findById(dto.deliveryZoneId())orElse(null) )")
    @Mapping(target = "order", expression = "java( orderRepository.findById(dto.orderId()).orElse(null) )")
    public abstract OrderAdress toEntity(OrderAdressPostDto dto);

    public abstract OrderAdressDto toDto(OrderAdress entity);
}

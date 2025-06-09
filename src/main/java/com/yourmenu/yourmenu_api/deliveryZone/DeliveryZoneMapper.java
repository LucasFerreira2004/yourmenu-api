package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
import org.springframework.data.domain.Page;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class DeliveryZoneMapper {

    abstract DeliveryZoneDto toDto(DeliveryZone entity);

    abstract List<DeliveryZoneDto> toPageDto(List<DeliveryZone> zones);

    abstract DeliveryZone toEntity(DeliveryZonePostDto dto);
}

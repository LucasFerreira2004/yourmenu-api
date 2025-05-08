package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public abstract class DeliveryZoneMapper {

    abstract DeliveryZoneDto toDto(DeliveryZone entity);

    abstract Page<DeliveryZoneDto> toPageDto(Page<DeliveryZone> pages);

    abstract DeliveryZone toEntity(DeliveryZonePostDto dto);
}

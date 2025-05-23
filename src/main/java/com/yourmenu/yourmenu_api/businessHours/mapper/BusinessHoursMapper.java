package com.yourmenu.yourmenu_api.businessHours.mapper;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class BusinessHoursMapper {
    public BusinessHoursDTO toDTO(BusinessHours entity) {
        if (entity == null) {
            return null;
        }

        return new BusinessHoursDTO(
                entity.getId_businessHours(),
                entity.getWeekday(),
                entity.getOpeningTime(),
                entity.getClosingTime()
        );
    }

    public BusinessHours toEntity(BusinessHoursDTO dto, Restaurant restaurant) {
        if (dto == null || restaurant == null) {
            return null;
        }

        BusinessHours entity = new BusinessHours();
        entity.setId_businessHours(dto.id_businessHours());
        entity.setRestaurant(restaurant);
        entity.setWeekday(dto.weekday());
        entity.setOpeningTime(dto.openingTime());
        entity.setClosingTime(dto.closingTime());
        return entity;
    }
}

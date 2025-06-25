package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Mappers;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.stereotype.Component;

@Component
public class SizeOptionsMapper {
    public SizeOptionDTO toDTO(SizeOption entity){
        if(entity == null){
            return null;
        }
        return new SizeOptionDTO(
                entity.getId(),
                entity.getMagnitude(),
                entity.getMeasureUnit(),
                entity.getMeasureUnit().getAbbreviation()
        );
    }
}

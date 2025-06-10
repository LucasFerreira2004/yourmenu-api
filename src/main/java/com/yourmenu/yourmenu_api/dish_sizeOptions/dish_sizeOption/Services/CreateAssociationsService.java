package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateAssociationsService {

    public void execute(List<SizeOptionPriceDTO> dto, Dish dish){
        for(int i = 0; i < dto.size(); i++){
            DishSizeOption dishSizeOption = new DishSizeOption();
        }
    }
}

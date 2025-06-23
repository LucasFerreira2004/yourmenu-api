package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateAssociationsService {

    @Autowired
    DishSizeOptionRepository dishSizeOptionRepository;

    @Autowired
    CreateAssociationsService createAssociationsService;

    public void execute(List<SizeOptionPriceDTO> dto, Dish dish){
        System.out.println(dish.getSizeOptions());

        /*
        for(int i = 0; i < dish.getSizeOptions().size(); i++){ //apagar os atuais
            dishSizeOptionRepository.deleteById(dish.getSizeOptions().get(i).getId());
        }
        */
        createAssociationsService.execute(dto, dish); // reaproveitando metodo para guardar os novos
    }
}

package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateAssociationsService {

    @Autowired
    private DishSizeOptionRepository dishSizeOptionRepository;

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    public void execute(List<SizeOptionPriceDTO> dto, Dish dish){
        for(int i = 0; i < dto.size(); i++){
            SizeOptionPriceDTO item = dto.get(i);

            DishSizeOption dishSizeOption = new DishSizeOption();
            dishSizeOption.setDish(dish);

            SizeOption sizeOption = sizeOptionRepository.findById(item.sizeOptionId())
                    .orElseThrow(() -> new RuntimeException("SizeOption não encontrado com ID: " + item.sizeOptionId()));

            dishSizeOption.setSizeOption(sizeOption);
            dishSizeOption.setPrice(item.price());

            dishSizeOptionRepository.save(dishSizeOption);

            System.out.println(dishSizeOption);
        }
    }

}

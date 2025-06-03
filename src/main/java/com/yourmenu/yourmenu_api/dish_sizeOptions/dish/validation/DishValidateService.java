package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishValidateService {
    @Autowired
    private DishRepository dishRepository;

    public void validateDishIsUniqueByName(Dish dish) {
            dishRepository.findByNameAndRestaurant(dish.name, dish.getRestaurant().getId());
    }
}

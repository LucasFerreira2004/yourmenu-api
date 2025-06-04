package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation;

import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceWithSameNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishValidateService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryValidateService categoryValidateService;

    public void validateDishIsUniqueByName(Dish dish) {
            Dish result = dishRepository.findByNameAndRestaurant(dish.name, dish.getRestaurant().getId());
            if (result != null)
                throw new ResourceWithSameNameException("dish");
    }
    public void validateToSave(Dish dish, String adminId) {
        categoryValidateService.validateAdminCanEditCategory(dish.category.getId(), adminId);
        this.validateDishIsUniqueByName(dish);
    }
}

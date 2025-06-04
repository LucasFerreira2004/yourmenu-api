package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation;

import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.shared.globalExceptions.EntityDoesNotBelongToAnotherEntityException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceWithSameNameException;
import jakarta.persistence.EntityNotFoundException;
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

    public void validateDishBelongsToRestaurant(Dish dish, String restaurantId) {
        if (!dish.getRestaurant().getId().equals(restaurantId))
            throw new EntityDoesNotBelongToAnotherEntityException("dish","restaurant");
    }

    public void validateDishExists(Long dishId){
        dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
    }
    public void validateToGetById(Long dishId, String restaurantId){
        this.validateDishExists(dishId);
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
        this.validateDishBelongsToRestaurant(dish, restaurantId);
    }
}

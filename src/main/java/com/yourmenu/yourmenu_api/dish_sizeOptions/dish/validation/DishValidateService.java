package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation;

import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.shared.globalExceptions.EntityDoesNotBelongToAnotherEntityException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceWithSameNameException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DishValidateService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private CategoryValidateService categoryValidateService;

    @Autowired
    private RestaurantValidateService restaurantValidateService;

    public void validateDishIsUniqueByName(Dish dish) {
            Dish result = dishRepository.findByNameAndRestaurant(dish.getName(), dish.getRestaurant().getId());
            if (result != null)
                throw new ResourceWithSameNameException("dish");
    }
    public void validateToSave(Dish dish, String adminId) {
        categoryValidateService.validateAdminCanEditCategory(dish.getCategory().getId(), adminId);
        restaurantValidateService.validateAdministratorCanEditOrViewRestaurant(dish.getRestaurant().getId(), adminId);
        this.validateDishIsUniqueByName(dish);
    }

    public void validateToUpdate(Long dihsId, Dish newDish,String adminId) {
        this.validateDishExists(dihsId);
        categoryValidateService.validateAdminCanEditCategory(newDish.getCategory().getId(), adminId);
        restaurantValidateService.validateAdministratorCanEditOrViewRestaurant(newDish.getRestaurant().getId(), adminId);
        Dish oldDish = dishRepository.findById(dihsId).orElseThrow(EntityNotFoundException::new);
        if (!newDish.getName().equals(oldDish.getName()))
            this.validateDishIsUniqueByName(newDish);

    }

    public void validateDishBelongsToRestaurant(Dish dish, String restaurantId) {
        if (!dish.getRestaurant().getId().equals(restaurantId))
            throw new EntityDoesNotBelongToAnotherEntityException("dish","restaurant");
    }

    public void validateDishBelongsToCategory(Dish dish, Long categoryId) {
        if (!Objects.equals(dish.getCategory().getId(), categoryId))
            throw new EntityDoesNotBelongToAnotherEntityException("dish","category");
    }

    public void validateDishExists(Long dishId){
        dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
    }

    public Dish validateDishExistsAndGet(Long dishId){
        return dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
    }
    public void validateToGetById(Long dishId, String restaurantId){
        this.validateDishExists(dishId);
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
        this.validateDishBelongsToRestaurant(dish, restaurantId);
    }

    public void validateToDelete(String restaurantId, Long categoryId, Long dishId, String adminId) {
        validateToGetById(dishId, restaurantId);
        Dish dish = validateDishExistsAndGet(dishId);
        validateDishBelongsToCategory(dish, categoryId);
        categoryValidateService.validateAdminCanEditCategory(dish.getCategory().getId(), adminId);
        restaurantValidateService.validateAdministratorCanEditOrViewRestaurant(dish.getRestaurant().getId(), adminId);
    }
}

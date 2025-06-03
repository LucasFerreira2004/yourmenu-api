package com.yourmenu.yourmenu_api.category.validation;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryDoesntBelongToRestaurantException;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.shared.globalExceptions.DuplicatedNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryValidateService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantValidateService restaurantValidateService;

    public void validateCategoryExists(Long categoryId) {
        categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());
    }
    public void validateAdminCanSaveCategory(Category category, String adminId) {
        restaurantValidateService.validateRestaurantExists(category.getRestaurant());
        restaurantValidateService.validateAdministratorCanEditRestaurant(category.getRestaurant(), adminId);
        this.validateCategoryIsUnique(category);
    }

    public void validateAdminCanEditCategory(Long categoryId, String adminId) {
        validateCategoryExists(categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException());
        restaurantValidateService.validateRestaurantExists(category.getRestaurant());
        restaurantValidateService.validateAdministratorCanEditRestaurant(category.getRestaurant(), adminId);
    }
    public void validateCategorybelongsToRestaurant(Category category, String restaurantId) {
        if (!category.getRestaurant().getId().equals(restaurantId)) {
            throw new CategoryDoesntBelongToRestaurantException();
        }
    }
    public void validateCategoryIsUnique(Category category) {
        System.out.println("Depurando id do adm: " + category.getRestaurant().getAdministrator().getId());
        restaurantValidateService.validateRestaurantExists(category.getRestaurant().getId());
        Category categoryWithSameName = categoryRepository.findByNameAndRestaurantId(category.getName(), category.getRestaurant().getId());
        if (categoryWithSameName != null) throw new DuplicatedNameException("category");
    }
}

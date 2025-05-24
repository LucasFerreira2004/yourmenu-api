package com.yourmenu.yourmenu_api.category.validation;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryDoesntBelongToRestaurantException;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryNotFoundException;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
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
    public void validateAdminCanSaveCategory(Long categoryId, String adminId) {
        validateCategoryExists(categoryId);
        restaurantValidateService.

    }

    public void validateCategorybelongsToRestaurant(Category category, String restaurantId) {
        if (!category.getRestaurant().getId().equals(restaurantId)) {
            throw new CategoryDoesntBelongToRestaurantException();
        }
    }
}

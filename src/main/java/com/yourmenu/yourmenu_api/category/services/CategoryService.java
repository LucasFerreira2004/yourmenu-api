package com.yourmenu.yourmenu_api.category.services;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryNotFoundException;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
import com.yourmenu.yourmenu_api.category.mapper.CategoryMapper;
import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantService;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryValidateService categoryValidateService;

    @Autowired
    private RestaurantValidateService restaurantValidateService;

    @Transactional
    public CategoryDTO save(CategorySaveDTO dto, String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantNotFoundException());
        Category category = CategoryMapper.toEntity(dto, restaurant);
        categoryValidateService.validateAdminCanSaveCategory(category, adminId);
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Transactional
    public CategoryDTO update(CategorySaveDTO dto, Long categoryId, String adminId) {
        categoryValidateService.validateAdminCanEditCategory(categoryId, adminId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        category.setName(dto.name());
        categoryValidateService.validateCategoryIsUnique(category);
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }
    public CategoryDTO getByCategoryId(Long categoryId, String restaurantId) {
        categoryValidateService.validateCategoryExists(categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException());
        categoryValidateService.validateCategorybelongsToRestaurant(category, restaurantId);
        return CategoryMapper.toDto(category);
    }

    public List<CategoryDTO> getAllByRestaurantId(String restaurantId) {
        restaurantValidateService.validateRestaurantExists(restaurantId);
        List<Category> categories = categoryRepository.findAllByRestaurantId(restaurantId);
        return categories.stream()
                .map(CategoryMapper::toDto)
                .toList();
    }
    public CategoryDTO delete(Integer categoryid, String AdminId){
        return null;
    }
}

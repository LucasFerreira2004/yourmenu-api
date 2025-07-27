package com.yourmenu.yourmenu_api.category.services;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryAssociatedWithDishException;
import com.yourmenu.yourmenu_api.category.Exceptions.CategoryNotFoundException;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
import com.yourmenu.yourmenu_api.category.mapper.CategoryMapper;
import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
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

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    public CategoryDTO save(CategorySaveDTO dto, String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(RestaurantNotFoundException::new);
        Category category = CategoryMapper.toEntity(dto, restaurant);
        categoryValidateService.validateAdminCanSaveCategory(category, adminId);
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }

    @Transactional
    public CategoryDTO update(CategorySaveDTO dto, String restaurantId, Long categoryId, String adminId) {
        categoryValidateService.validateAdminCanEditCategory(categoryId, adminId);
        Category oldCategory = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        //no futuro deverá ser crida uma função para essa parte de criação de  categoria
        Category newCategory = CategoryMapper.copyEntity(oldCategory);
        newCategory.setName(dto.name().toLowerCase());
        System.out.println(categoryRepository.findById(categoryId));
        categoryValidateService.validateCategoryIsUnique(newCategory);
        categoryRepository.save(newCategory);
        return CategoryMapper.toDto(newCategory);
    }

    public CategoryDTO getByCategoryId(Long categoryId, String restaurantId) {
        categoryValidateService.validateCategoryExists(categoryId);
        categoryValidateService.validateCategorybelongsToRestaurant(categoryId, restaurantId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        return CategoryMapper.toDto(category);
    }

    public List<CategoryDTO> getAllByRestaurantId(String restaurantId) {
        restaurantValidateService.validateRestaurantExists(restaurantId);
        List<Category> categories = categoryRepository.findAllByRestaurantId(restaurantId);
        return categories.stream()
                .map(CategoryMapper::toDto)
                .toList();
    }
    @Transactional
    public CategoryDTO delete(Long categoryId, String adminId) {
        categoryValidateService.validateAdminCanEditCategory(categoryId, adminId);
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(CategoryNotFoundException::new);

        boolean hasDishes = dishRepository.existsByCategoryId(categoryId);
        if (hasDishes) {
            throw new CategoryAssociatedWithDishException();
        }
        categoryRepository.delete(category);
        return CategoryMapper.toDto(category);
    }

    @Transactional
    public void createCategoriesDefault(Restaurant restaurant) {
        categoryRepository.save(new Category(null, restaurant, "Marmitas"));
        categoryRepository.save(new Category(null, restaurant, "Hamburgueres"));
        categoryRepository.save(new Category(null, restaurant, "Lanches"));
        categoryRepository.save(new Category(null, restaurant, "Bebidas"));
        categoryRepository.save(new Category(null, restaurant, "Sobremesas"));
    }
}

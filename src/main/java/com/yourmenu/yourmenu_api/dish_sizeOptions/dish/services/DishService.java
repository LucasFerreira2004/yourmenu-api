package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services;


import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.mappers.DishMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation.DishValidateService;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DishValidateService dishValidateService;

    public DishDTO save(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("restaurant"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category"));
        Dish dish = DishMapper.toEntity(dto, restaurant, category);
        dishValidateService.validateToSave(dish, adminId);
        return DishMapper.toDTO(dishRepository.save(dish));
    }

    public DishDTO update(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        return null;
    }

    public DishDTO delete(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        return null;
    }

    public DishDTO getById(Long dishId, String restaurantId){
        dishValidateService.validateToGetById(dishId, restaurantId);
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
        return DishMapper.toDTO(dish);
    }

    public List<DishDTO> getAllDishesByCategory(String restaurantId, Long categoryId){
        List<Dish> dishes = dishRepository.findAllByCategoryId(categoryId);
        return dishes.stream().map(x -> DishMapper.toDTO(x)).toList();
    }
    public List<DishDTO> getAllAvailableDishesByCategory(Long dishId, Long categoryId){
        return List.of();
    }

}

package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services;

import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
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


    public DishDTO save(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        return null;
    }

    public DishDTO update(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        return null;
    }

    public DishDTO delete(DishSaveDTO dto, String restaurantId, Long categoryId, String adminId){
        return null;
    }

    public DishDTO getBydId(Long dishId){
        return null;
    }

    public List<DishDTO> getAllDishesByCategory(Long dishId, Long categoryId){
        return List.of();
    }

    public List<DishDTO> getAllAvailableDishesByCategory(Long dishId, Long categoryId){
        return List.of();
    }

}

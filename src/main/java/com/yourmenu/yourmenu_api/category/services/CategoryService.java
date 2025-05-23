package com.yourmenu.yourmenu_api.category.services;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
import com.yourmenu.yourmenu_api.category.mapper.CategoryMapper;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantService;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO save(CategorySaveDTO dto, String restaurantId, String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()-> new RestaurantNotFoundException("restaurantId"));
        Category category = CategoryMapper.toEntity(dto, restaurant);
        categoryRepository.save(category);
        return CategoryMapper.toDto(category);
    }
    public CategoryDTO update(CategorySaveDTO dto, String AdminId){
        return null;
    }
    public CategoryDTO getByCategoryId(Integer categoryid){
        return null;
    }
    public CategoryDTO getAllByRestaurantId(String RestaurantId){
        return null;
    }
    public CategoryDTO delete(Integer categoryid, String AdminId){
        return null;
    }
}

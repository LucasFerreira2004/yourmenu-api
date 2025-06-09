package com.yourmenu.yourmenu_api.category.mapper;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;

public class CategoryMapper {
    public static Category toEntity(CategorySaveDTO dto, Restaurant restaurant) {
        Category category = new Category();
        category.setName(dto.name().toLowerCase());
        category.setRestaurant(restaurant);
        return category;
    }

    public static CategoryDTO toDto(Category category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getRestaurant().getId());
    }

    public static Category copyEntity(Category category) {
        return new Category(category.getId(),
                            category.getRestaurant(),
                            category.getName());}
}

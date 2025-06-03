package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.mappers;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;

import java.util.List;

public class DishMapper {

    public static Dish toEntity(DishSaveDTO dto, Restaurant restaurant, Category category) {
        Dish dish = new Dish();
        dish.setName(dto.name().toLowerCase());
        if (dto.description() != null)
            dish.setDescription(dto.description().toLowerCase());
        if (dto.isAvailable() != null)
            dish.setIsAvailable(dto.isAvailable());
        else
            dish.setIsAvailable(true);
        dish.setImageUrl(dto.imgUrl());
        dish.setRestaurant(restaurant);
        dish.setCategory(category);
        return dish;
    }

    public static DishDTO toDTO(Dish dish) {
        return new DishDTO(
                dish.getId(),
                dish.getRestaurant() != null ? dish.getRestaurant().getId() : null,
                dish.getCategory() != null ? dish.getCategory().getId() : null,
                dish.getName(),
                dish.getDescription(),
                dish.getIsAvailable(),
                dish.getImageUrl(),
                List.of() // Placeholder para sizeOptionsPrices
        );
    }
}


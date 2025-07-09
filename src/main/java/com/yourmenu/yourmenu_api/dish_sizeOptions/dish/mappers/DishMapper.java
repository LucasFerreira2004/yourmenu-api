package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.mappers;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishMapper {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    public Dish toEntity(DishSaveDTO dto, String restaurantId, Long categoryId) {
        Dish dish = new Dish();
        dish.setName(dto.name().toLowerCase());
        if (dto.description() != null)
            dish.setDescription(dto.description().toLowerCase());
        if (dto.isAvailable() != null)
            dish.setIsAvailable(dto.isAvailable());
        else
            dish.setIsAvailable(true);
        dish.setImageUrl(dto.imgUrl());
        dish.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("Restaurant")));
        dish.setCategory(categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category")));
        return dish;
    }

    public static DishDTO toDTO(Dish dish) {
        List<SizeOptionPriceDTO> sizeOptionsPrices = dish.getSizeOptions() != null
                ? dish.getSizeOptions().stream()
                .map(opt -> new SizeOptionPriceDTO(
                        opt.getId(),
                        opt.getSizeOption().getId(),
                        opt.getSizeOption().getMagnitude(),
                        opt.getSizeOption().getMeasureUnit().getAbbreviation(),
                        opt.getPrice()
                ))
                .toList()
                : List.of(); // Retorna lista vazia caso seja null

        return new DishDTO(
                dish.getId(),
                dish.getRestaurant() != null ? dish.getRestaurant().getId() : null,
                dish.getCategory() != null ? dish.getCategory().getId() : null,
                dish.getName(),
                dish.getDescription(),
                dish.getIsAvailable(),
                dish.getImageUrl(),
                sizeOptionsPrices
        );
    }

}


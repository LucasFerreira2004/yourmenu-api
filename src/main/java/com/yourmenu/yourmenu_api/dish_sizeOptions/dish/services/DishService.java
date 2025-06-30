package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services;


import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.mappers.DishMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation.DishValidateService;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.shared.awss3.S3Service;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private CategoryValidateService categoryValidateService;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private S3Service s3Service;

    public DishDTO save(
            @Valid DishSaveDTO dto,
            MultipartFile imageUrl,
            String restaurantId,
            Long categoryId,
            String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("restaurant"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category"));
        Dish dish = DishMapper.toEntity(dto, restaurant, category);

        if(imageUrl != null && !imageUrl.isEmpty())
            dish.setImageUrl(s3Service.uploadFile(imageUrl));

        dishValidateService.validateToSave(dish, adminId);
        return DishMapper.toDTO(dishRepository.save(dish));
    }

    public DishDTO update(
            Long dishId,
            @Valid DishSaveDTO newDishDTO,
            MultipartFile imageUrl,
            String restaurantId,
            Long categoryId,
            String adminId) {
        Dish newDish = dishMapper.toEntity(newDishDTO, restaurantId, categoryId);
        dishValidateService.validateToUpdate(dishId, newDish, adminId);
        newDish.setId(dishId);

        if(imageUrl != null && !imageUrl.isEmpty())
            newDish.setImageUrl(s3Service.uploadFile(imageUrl));

        Dish updatedDish = dishRepository.save(newDish);
        return DishMapper.toDTO(updatedDish);
    }

    public void delete(String restaurantId, Long categoryId, Long dishId,  String adminId){
        dishValidateService.validateToDelete(restaurantId, categoryId, dishId, adminId);
        dishRepository.deleteById(dishId);
    }

    public DishDTO getById(Long dishId, String restaurantId){
        dishValidateService.validateToGetById(dishId, restaurantId);
        Dish dish = dishRepository.findById(dishId).orElseThrow(() -> new ResourceNotFoundException("dish"));
        return DishMapper.toDTO(dish);
    }

    public List<DishDTO> getAllDishesByCategory(String restaurantId, Long categoryId){
        categoryValidateService.validateCategorybelongsToRestaurant(categoryId, restaurantId);
        List<Dish> dishes = dishRepository.findAllByCategoryIdInRestaurant(categoryId, restaurantId);
        return dishes.stream().map(x -> DishMapper.toDTO(x)).toList();
    }
    public List<DishDTO> getAllAvailableDishesByCategory(String restaurantId, Long categoryId){
        categoryValidateService.validateCategorybelongsToRestaurant(categoryId, restaurantId);
        List<Dish> dishes = dishRepository.findAllAvailableByCategoryIdInRestaurant(categoryId, restaurantId);
        return dishes.stream().map(x -> DishMapper.toDTO(x)).toList();
    }

}

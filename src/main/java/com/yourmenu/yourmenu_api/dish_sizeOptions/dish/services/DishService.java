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
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.services.CreateAssociationsService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.services.UpdateAssociationsService;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.shared.awss3.ImageDefaultsProperties;
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

    @Autowired
    private CreateAssociationsService createAssociationsService;

    @Autowired
    private UpdateAssociationsService updateAssociationsService;

    @Autowired
    private ImageDefaultsProperties imageDefaultsProperties;

    public DishDTO save(
            @Valid DishSaveDTO dto,
            MultipartFile imageDish,
            String restaurantId,
            Long categoryId,
            String adminId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new ResourceNotFoundException("restaurant"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("category"));
        Dish dish = DishMapper.toEntity(dto, restaurant, category);

        adicionarImagens(dish, imageDish);

        dishValidateService.validateToSave(dish, adminId);

        Dish savedDish = dishRepository.save(dish);
        createAssociationsService.execute(dto.sizeOptionsPrices(), savedDish);
        savedDish = dishRepository.findById(savedDish.getId()) // Recarrega o prato com os relacionamentos atualizados
                .orElseThrow(() -> new ResourceNotFoundException("dish"));
        return DishMapper.toDTO(savedDish);
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
        updateAssociationsService.execute(newDishDTO.sizeOptionsPrices(), newDish);
        updatedDish = dishRepository.findById(updatedDish.getId()) // Recarrega o prato com os relacionamentos atualizados
                .orElseThrow(() -> new ResourceNotFoundException("dish"));

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

    private void adicionarImagens(Dish dish, MultipartFile imageDish) {
        if (imageDish != null && !imageDish.isEmpty()) {
            String imageUrl = s3Service.uploadFile(imageDish);
            dish.setImageUrl(imageUrl);
        } else {
            dish.setImageUrl(imageDefaultsProperties.getDefaultVisualDish());
        }
    }
}

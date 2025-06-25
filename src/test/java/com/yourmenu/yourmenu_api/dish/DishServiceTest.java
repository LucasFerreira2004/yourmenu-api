package com.yourmenu.yourmenu_api.dish;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.category.CategoryRepository;
import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.DishRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.mappers.DishMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services.DishService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.validation.DishValidateService;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.shared.globalExceptions.EntityDoesNotBelongToAnotherEntityException;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.internal.configuration.GlobalConfiguration.validate;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {

    @InjectMocks
    private DishService dishService;

    @Mock
    private DishRepository dishRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private DishValidateService dishValidateService;

    @Mock
    private CategoryValidateService categoryValidateService;

    private Restaurant restaurant;
    private Category category;
    private Dish dish;
    private Dish dish2;

    @BeforeEach
    void setUp() {
        restaurant = new Restaurant();
        restaurant.setId("restaurant-id");

        category = new Category();
        category.setId(1L);
        category.setRestaurant(restaurant);

        dish = new Dish();
        dish.setId(100L);
        dish.setName("Feijoada");
        dish.setDescription("Completa");
        dish.setImageUrl("http://img.com/feijoada.jpg");
        dish.setIsAvailable(true);
        dish.setRestaurant(restaurant);
        dish.setCategory(category);

        dish2 = new Dish();
        dish2.setId(101L);
        dish2.setName("Macarronada");
        dish2.setDescription("Sem glutem");
        dish2.setImageUrl("http://img.com/macarronada.jpg");
        dish2.setIsAvailable(true);
        dish2.setRestaurant(restaurant);
        dish2.setCategory(category);

    }

    @Test
    void shouldSaveDishSuccessfully() {
        DishSaveDTO dto = new DishSaveDTO("Feijoada", "Completa", true, "http://img.com/feijoada.jpg", List.of());

        when(restaurantRepository.findById("restaurant-id")).thenReturn(Optional.of(restaurant));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        MultipartFile multipartFile = mock(MultipartFile.class);
        DishDTO result = dishService.save(dto, multipartFile, "restaurant_id",1L, "admin-id");

        assertNotNull(result);
        assertEquals("Feijoada", result.name());
        verify(dishValidateService).validateToSave(any(Dish.class), eq("admin-id"));
    }

    @Test
    void shouldThrowIfRestaurantNotFound() {
        DishSaveDTO dto = new DishSaveDTO("Feijoada", "Completa", true, "url", List.of());
        when(restaurantRepository.findById("restaurant-id")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            MultipartFile multipartFile = mock(MultipartFile.class);
            dishService.save(dto, multipartFile, "restaurant_id",1L, "admin-id");
        });
    }

    @Test
    void shouldThrowIfCategoryNotFound() {
        DishSaveDTO dto = new DishSaveDTO("Feijoada", "Completa", true, "url", List.of());
        when(restaurantRepository.findById("restaurant-id")).thenReturn(Optional.of(restaurant));
        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            MultipartFile multipartFile = mock(MultipartFile.class);
            dishService.save(dto, multipartFile, "restaurant_id",1L, "admin-id");
        });
    }

    @Test
    void shouldReturnDishByIdSuccessfully() {
        when(dishRepository.findById(100L)).thenReturn(Optional.of(dish));

        DishDTO result = dishService.getById(100L, "restaurant-id");

        assertNotNull(result);
        assertEquals("Feijoada", result.name());
        verify(dishValidateService).validateToGetById(100L, "restaurant-id");
    }

    @Test
    void shouldThrowWhenDishNotFoundById() {
        when(dishRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            dishService.getById(100L, "restaurant-id");
        });

        verify(dishValidateService).validateToGetById(100L, "restaurant-id");
    }

    @Test
    void shoudReturnDishListByCategorySucessfully() {
        //configurar
        List<Dish> dishes = List.of(dish, dish2);
        when(dishRepository.findAllByCategoryIdInRestaurant(category.getId(), restaurant.getId())).thenReturn(dishes);
        //exercitar
        List<DishDTO> response = dishService.getAllDishesByCategory(restaurant.getId(), category.getId());
        //verificar
        List<DishDTO> rightDTOs = List.of(DishMapper.toDTO(dish),  DishMapper.toDTO(dish2));
        assertEquals(response, rightDTOs);
    }

    @Test
    void getByCategory_ShoudThrowException_WhenCategoryDoesNotBelongToRestaurant() {
        final Long WRONG_CATEGORY_ID = category.getId() + 10L;
        doThrow(new EntityDoesNotBelongToAnotherEntityException("category", "restaurant"))
                .when(categoryValidateService)
                .validateCategorybelongsToRestaurant(WRONG_CATEGORY_ID, restaurant.getId());

        assertThrows(EntityDoesNotBelongToAnotherEntityException.class, () -> {
            dishService.getAllDishesByCategory(restaurant.getId(), WRONG_CATEGORY_ID);
        }, "method didn't throw exception when category did not belong to restaurant");
    }
}

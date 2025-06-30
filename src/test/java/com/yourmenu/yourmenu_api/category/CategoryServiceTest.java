package com.yourmenu.yourmenu_api.category;
//
//import com.yourmenu.yourmenu_api.category.Exceptions.CategoryNotFoundException;
//import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
//import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
//import com.yourmenu.yourmenu_api.category.services.CategoryService;
//import com.yourmenu.yourmenu_api.category.validation.CategoryValidateService;
//import com.yourmenu.yourmenu_api.restaurant.Restaurant;
//import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
//import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
//
//    @InjectMocks
//    private CategoryService categoryService;
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    @Mock
//    private RestaurantRepository restaurantRepository;
//
//    @Mock
//    private CategoryValidateService categoryValidateService;
//
//    private Restaurant restaurant;
//    private Category category;
//
//    @BeforeEach
//    void setUp() {
//        restaurant = new Restaurant();
//        restaurant.setId("rest1");
//
//        category = new Category();
//        category.setId(1L);
//        category.setName("Bebidas");
//        category.setRestaurant(restaurant);
//    }
//
//    @Test
//    void save_ShouldReturnCategoryDTO_WhenDataIsValid() {
//        // Arrange
//        CategorySaveDTO dto = new CategorySaveDTO("Bebidas");
//
//        when(restaurantRepository.findById("rest1")).thenReturn(Optional.of(restaurant));
//        doNothing().when(categoryValidateService).validateAdminCanSaveCategory(any(), eq("admin1"));
//        when(categoryRepository.save(any(Category.class))).thenAnswer(invocation -> {
//            Category c = invocation.getArgument(0);
//            c.setId(1L);
//            return c;
//        });
//
//        // Act
//        CategoryDTO result = categoryService.save(dto, "rest1", "admin1");
//
//        // Assert
//        assertEquals("bebidas", result.name());
//        assertEquals(1L, result.Id());
//    }
//
//    @Test
//    void update_ShouldUpdateCategory_WhenValid() {
//        // Arrange
//        CategorySaveDTO dto = new CategorySaveDTO("Novas bebidas");
//
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//        doNothing().when(categoryValidateService).validateAdminCanEditCategory(1L, "admin1");
//        doNothing().when(categoryValidateService).validateCategoryIsUnique(any());
//        when(categoryRepository.save(any(Category.class))).thenAnswer(i -> i.getArgument(0));
//
//        // Act
//        CategoryDTO result = categoryService.update(dto, "rest1", 1L, "admin1");
//
//        // Assert
//        assertEquals("novas bebidas", result.name());
//        assertEquals(1L, result.Id());
//    }
//
//    @Test
//    void save_ShouldThrowException_WhenRestaurantNotFound() {
//        // Arrange
//        CategorySaveDTO dto = new CategorySaveDTO("Test");
//        when(restaurantRepository.findById("invalid")).thenReturn(Optional.empty());
//
//        // Act + Assert
//        assertThrows(RestaurantNotFoundException.class, () ->
//                categoryService.save(dto, "invalid", "admin1"));
//    }
//
//    @Test
//    void update_ShouldThrowException_WhenCategoryNotFound() {
//        // Arrange
//        CategorySaveDTO dto = new CategorySaveDTO("Test");
//        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());
//        doNothing().when(categoryValidateService).validateAdminCanEditCategory(999L, "admin1");
//
//        // Act + Assert
//        assertThrows(CategoryNotFoundException.class, () ->
//                categoryService.update(dto, "rest1", 999L, "admin1"));
//    }
//
//    @Test
//    void delete_ShouldRemoveCategory_WhenValid() {
//        // Arrange
//        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//        doNothing().when(categoryValidateService).validateAdminCanEditCategory(1L, "admin1");
//        doNothing().when(categoryRepository).delete(category);
//
//        // Act
//        CategoryDTO result = categoryService.delete(1L, "admin1");
//
//        // Assert
//        assertEquals("Bebidas", result.name());
//        assertEquals(1L, result.Id());
//        verify(categoryRepository).delete(category); // Garante que o delete foi chamado
//    }
//
//    @Test
//    void delete_ShouldThrowException_WhenCategoryNotFound() {
//        // Arrange
//        when(categoryRepository.findById(999L)).thenReturn(Optional.empty());
//        doNothing().when(categoryValidateService).validateAdminCanEditCategory(999L, "admin1");
//
//        // Act + Assert
//        assertThrows(CategoryNotFoundException.class, () ->
//                categoryService.delete(999L, "admin1"));
//    }
}

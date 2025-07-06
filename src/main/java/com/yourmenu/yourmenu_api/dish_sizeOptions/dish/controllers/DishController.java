package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services.DishService;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}")
public class DishController {
    @Autowired
    private DishService dishService;

    private final String URL_WITH_CATEGORY = "/category/{categoryId}/dish";
    private final String URL_WITHOUT_CATEGORY = "/dish";

    @PostMapping(value = URL_WITH_CATEGORY, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DishDTO> createDish(
            @RequestPart("dto") @Validated DishSaveDTO dto,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl,
            @PathVariable String restaurantId,
            @PathVariable Long categoryId,
            @CurrentUser Administrator currentUser) {
        DishDTO response = dishService.save(dto, imageUrl, restaurantId, categoryId, currentUser.getId());
        URI location = URI.create("/restaurant/"+restaurantId+"/category/" + categoryId + "/dish/" + response.id());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(response);
    }

    @GetMapping(URL_WITHOUT_CATEGORY + "/{dishId}")
    public ResponseEntity<DishDTO> getDishById(@PathVariable String restaurantId, @PathVariable Long dishId) {
        DishDTO response = dishService.getById(dishId, restaurantId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(URL_WITH_CATEGORY)
    public ResponseEntity<List<DishDTO>> getAllDishesByCategory(@PathVariable String restaurantId,
                                                                @PathVariable Long categoryId) {
        List<DishDTO> response = dishService.getAllDishesByCategory(restaurantId, categoryId);
        return ResponseEntity.ok(response);
    }

    @GetMapping(URL_WITH_CATEGORY + "/available")
    public ResponseEntity<List<DishDTO>> getAllAvailableDishesByCategory(@PathVariable String restaurantId,
                                                                         @PathVariable Long categoryId){
        List<DishDTO> response = dishService.getAllAvailableDishesByCategory(restaurantId, categoryId);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = URL_WITH_CATEGORY + "/{dishId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DishDTO> updateDish(
            @RequestPart("dto") @Validated DishSaveDTO dto,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl,
            @PathVariable String restaurantId,
            @PathVariable Long categoryId,
            @PathVariable Long dishId,
            @CurrentUser Administrator currentUser){
        DishDTO response = dishService.update(dishId,  dto, imageUrl, restaurantId, categoryId, currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(URL_WITH_CATEGORY + "/{dishId}")
    public ResponseEntity<Void> deleteDish(@PathVariable String restaurantId,
                                              @PathVariable Long categoryId,
                                              @PathVariable Long dishId,
                                              @CurrentUser Administrator currentUser) {
        dishService.delete(restaurantId, categoryId, dishId, currentUser.getId());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = URL_WITHOUT_CATEGORY + "/{dishId}/visual", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DishDTO> updateVisualDish(
            @PathVariable(value = "restaurantId") String restaurantId,
            @PathVariable(value = "dishId") Long dishId,
            @RequestPart(value = "imageUrl", required = false) MultipartFile imageUrl) {
        return ResponseEntity.ok().body(dishService.updateImageDish(restaurantId, dishId, imageUrl));
    }

    @PatchMapping(value = URL_WITHOUT_CATEGORY + "/{dishId}/visual")
    public ResponseEntity<DishDTO> deleteVisualDish(
            @PathVariable(value = "restaurantId") String restaurantId,
            @PathVariable(value = "dishId") Long dishId) {
        return ResponseEntity.ok().body(dishService.deleteImageDish(restaurantId, dishId));
    }
}

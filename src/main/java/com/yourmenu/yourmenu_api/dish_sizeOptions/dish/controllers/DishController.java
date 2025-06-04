package com.yourmenu.yourmenu_api.dish_sizeOptions.dish.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.dto.DishSaveDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.services.DishService;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/restaurant/{restaurantId}/category/{categoryId}/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @PostMapping
    public ResponseEntity<DishDTO> saveCategory(@RequestBody @Valid DishSaveDTO dto,
                                                    @PathVariable String restaurantId,
                                                    @PathVariable Long categoryId,
                                                    @CurrentUser Administrator currentUser) {
        DishDTO response = dishService.save(dto, restaurantId, categoryId, currentUser.getId());
        URI location = URI.create("/restaurant/"+restaurantId+"/category/" + categoryId + "/dish/" + response.id());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(response);
    }
}

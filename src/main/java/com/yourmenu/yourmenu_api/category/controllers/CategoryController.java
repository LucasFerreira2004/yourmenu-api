package com.yourmenu.yourmenu_api.category.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.category.dto.CategoryDTO;
import com.yourmenu.yourmenu_api.category.dto.CategorySaveDTO;
import com.yourmenu.yourmenu_api.category.services.CategoryService;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/restaurant/{restaurantId}/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@PathVariable String restaurantId, @RequestBody @Valid CategorySaveDTO dto, @CurrentUser Administrator currentUser) {
        CategoryDTO aswer = categoryService.save(dto, restaurantId, currentUser.getId());
        URI location = URI.create("/restaurant/"+restaurantId+"/category/" + aswer.Id());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(aswer);
    }
}

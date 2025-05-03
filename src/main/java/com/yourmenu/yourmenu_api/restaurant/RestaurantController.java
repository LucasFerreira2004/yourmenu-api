package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value="/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> save(@RequestBody @Valid RestaurantSaveDTO dto) {
        RestaurantDTO createdRestaurant = restaurantService.save(dto);

        URI location = URI.create("/restaurants/" + createdRestaurant.slug());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(createdRestaurant);
    }
}

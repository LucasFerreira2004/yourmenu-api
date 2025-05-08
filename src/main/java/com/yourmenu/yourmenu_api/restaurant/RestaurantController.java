package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<RestaurantDTO> save(@RequestBody @Valid RestaurantSaveDTO dto, @CurrentUser Administrator currentUser) {
        System.out.println("ID DO USER: " + currentUser.getId()); //linha apenas para depuração
        RestaurantDTO createdRestaurant = restaurantService.save(dto, currentUser.getId());

        URI location = URI.create("/restaurants/" + createdRestaurant.slug());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(createdRestaurant);
    }

    @PatchMapping("/is-open/{restaurantId}")
    public ResponseEntity<RestaurantDTO> openClose(@RequestBody @Valid OpenDTO isOpen, @PathVariable String restaurantId, @CurrentUser Administrator currentUser) {
        RestaurantDTO response = restaurantService.openClose(isOpen, restaurantId,currentUser.getId());
        return ResponseEntity
                .ok()
                .body(response);
    }

    @PutMapping("/{slug}")
    public ResponseEntity<RestaurantDTO> update(@RequestBody @Valid RestaurantSaveDTO dto, @PathVariable String slug ,@CurrentUser Administrator currentUser) {
        RestaurantDTO response = restaurantService.update(dto, slug, currentUser.getId());
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> findAllByLoggedUser(@CurrentUser Administrator currentUser) {
        List<RestaurantDTO> response = restaurantService.findAllByLoggedUser(currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<RestaurantDTO> findBySlug(@PathVariable String slug, @CurrentUser Administrator currentUser) {
        RestaurantDTO response = restaurantService.findBySlug(slug, currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity<Void> delete(@PathVariable String slug, @CurrentUser Administrator currentUser) {
        restaurantService.delete(slug, currentUser.getId());
        return ResponseEntity.noContent().build();
    }

}

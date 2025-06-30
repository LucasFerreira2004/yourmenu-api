package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value="/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantDTO> save(
            @ModelAttribute RestaurantSaveDTO dto,
            @RequestParam(required = false, name = "profilePictureUrl") MultipartFile profilePictureUrl,
            @RequestParam(required = false, name = "bannerPictureUrl") MultipartFile bannerPictureUrl,
            @CurrentUser Administrator currentUser) {
//        logger.info("ID DO USER: " + currentUser.getId());

        RestaurantDTO createdRestaurant = restaurantService.save(dto, profilePictureUrl, bannerPictureUrl, currentUser.getId());

        URI location = URI.create("/restaurant/" + createdRestaurant.slug());
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

    @PutMapping(value = "/{restaurantId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantDTO> update(
            @ModelAttribute RestaurantSaveDTO dto,
            @PathVariable String restaurantId,
            @RequestPart(required = false, name = "profilePictureUrl") MultipartFile profilePictureUrl,
            @RequestPart(required = false, name = "bannerPictureUrl") MultipartFile bannerPictureUrl,
            @CurrentUser Administrator currentUser) {
        RestaurantDTO response = restaurantService.update(dto, restaurantId, profilePictureUrl, bannerPictureUrl, currentUser.getId());
        return ResponseEntity
                .ok()
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> findAllByLoggedUser(@CurrentUser Administrator currentUser) {
        List<RestaurantDTO> response = restaurantService.findAllByLoggedUser(currentUser.getId());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDTO> findBySlug(@PathVariable String restaurantId) {
        RestaurantDTO response = restaurantService.findById(restaurantId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Void> delete(@PathVariable String restaurantId, @CurrentUser Administrator currentUser) {
        restaurantService.delete(restaurantId, currentUser.getId());
        return ResponseEntity.noContent().build();
    }
}

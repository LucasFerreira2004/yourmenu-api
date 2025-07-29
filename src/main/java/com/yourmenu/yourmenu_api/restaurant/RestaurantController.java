package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.administrator.AdministratorService;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantLinkDto;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping(value="/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @Autowired
    private AdministratorService administratorService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantDTO> save(
            @ModelAttribute RestaurantSaveDTO dto,
            @RequestParam(required = false, name = "profilePictureUrl") MultipartFile profilePictureUrl,
            @RequestParam(required = false, name = "bannerPictureUrl") MultipartFile bannerPictureUrl,
            @CurrentUser Administrator currentUser) {

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
    public ResponseEntity<RestaurantDTO> findByLoggedUser(@CurrentUser Administrator currentUser) {
        RestaurantDTO response = restaurantService.findAllByLoggedUser(currentUser.getId());
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

    @PutMapping(value = "/{restaurantId}/profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantDTO> updateVisualProfileRestaurant(
            @PathVariable String restaurantId,
            @CurrentUser Administrator administrator,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().body(restaurantService.updateImageProfileRestaurant(restaurantId, administrator.getId(), image));
    }

    @PatchMapping(value = "/{restaurantId}/profile")
    public ResponseEntity<RestaurantDTO> deleteVisualProfileRestaurant(
            @PathVariable String restaurantId,
            @CurrentUser Administrator administrator) {
        return ResponseEntity.ok().body(restaurantService.deleteImageProfileRestaurant(restaurantId, administrator.getId()));
    }

    @PutMapping(value = "/{restaurantId}/banner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestaurantDTO> updateVisualBannerRestaurant(
            @PathVariable String restaurantId,
            @CurrentUser Administrator administrator,
            @RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().body(restaurantService.updateImageBannerRestaurant(restaurantId, administrator.getId(), image));
    }

    @PatchMapping(value = "/{restaurantId}/banner")
    public ResponseEntity<RestaurantDTO> deleteVisualBannerRestaurant(
            @PathVariable String restaurantId,
            @CurrentUser Administrator administrator) {
        return ResponseEntity.ok().body(restaurantService.deleteImageBannerRestaurant(restaurantId, administrator.getId()));
    }

    @GetMapping("/{restaurantId}/link")
    public ResponseEntity<RestaurantLinkDto> restaurantLink(
            @PathVariable String restaurantId) {
        RestaurantLinkDto link = restaurantService.gerarLink(restaurantId);
        return ResponseEntity.ok(link);
    }
}

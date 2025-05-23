package com.yourmenu.yourmenu_api.restaurantAddress;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressSaveDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/restaurantAdress")
public class RestaurantAddressController {
    @Autowired
    private RestaurantAddressService restaurantAddressService;

    @PostMapping
    public ResponseEntity<RestaurantAddressDTO> save (@RequestBody @Valid RestaurantAddressSaveDTO data, @CurrentUser Administrator currentUser) {
        RestaurantAddressDTO adressDTO = restaurantAddressService.save(data, currentUser.getId());
        URI location = URI.create("/restaurantAdress/" + adressDTO.restaurantId());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(adressDTO);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantAddressDTO> getbyRestaurantId(@PathVariable String restaurantId) {
        RestaurantAddressDTO adressDTO = restaurantAddressService.getRestaurantAdress(restaurantId);
        return ResponseEntity.ok(adressDTO);
    }

    @PutMapping
    public ResponseEntity<RestaurantAddressDTO> update (@RequestBody @Valid RestaurantAddressSaveDTO data, @CurrentUser Administrator currentUser) {
        RestaurantAddressDTO adressDTO = restaurantAddressService.update(data, currentUser.getId());
        return ResponseEntity
                .ok(adressDTO);
    }

}

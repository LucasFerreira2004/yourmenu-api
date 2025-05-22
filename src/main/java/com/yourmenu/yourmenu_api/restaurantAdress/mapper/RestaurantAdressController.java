package com.yourmenu.yourmenu_api.restaurantAdress.mapper;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurantAdress.RestaurantAdressService;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressDTO;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressSaveDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/restaurantAdress")
public class RestaurantAdressController {
    @Autowired
    private RestaurantAdressService restaurantAdressService;

    @PostMapping
    public ResponseEntity<RestaurantAdressDTO> save (@RequestBody @Valid RestaurantAdressSaveDTO data, @CurrentUser Administrator currentUser) {
        RestaurantAdressDTO adressDTO = restaurantAdressService.save(data, currentUser.getId());
        URI location = URI.create("/restaurantAdress/" + adressDTO.restaurantId());
        return ResponseEntity
                .created(location) // define o status 201 e o header Location
                .body(adressDTO);
    }

}

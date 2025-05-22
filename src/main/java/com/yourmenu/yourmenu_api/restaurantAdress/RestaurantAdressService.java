package com.yourmenu.yourmenu_api.restaurantAdress;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.RestaurantValidateService;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressDTO;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressSaveDTO;
import com.yourmenu.yourmenu_api.restaurantAdress.mapper.RestaurantAdressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantAdressService {
    @Autowired
    private RestaurantAdressRepository restaurantAdressRepository;

    @Autowired
    private RestaurantAdressValidateService restaurantAdressValidateService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transient
    public RestaurantAdressDTO save(RestaurantAdressSaveDTO dto, String adminId){
        restaurantAdressValidateService.validateAllToSave(dto.restaurantId(), adminId);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
        RestaurantAdress restaurantAdress = RestaurantAdressMapper.toEntity(dto, restaurant);
        restaurantAdressRepository.save(restaurantAdress);
        return RestaurantAdressMapper.toDTO(restaurantAdress);
    }
}

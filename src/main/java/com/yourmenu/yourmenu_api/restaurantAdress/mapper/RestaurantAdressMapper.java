package com.yourmenu.yourmenu_api.restaurantAdress.mapper;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurantAdress.RestaurantAdress;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressDTO;
import com.yourmenu.yourmenu_api.restaurantAdress.dto.RestaurantAdressSaveDTO;

public class RestaurantAdressMapper {

    public static RestaurantAdress toEntity(RestaurantAdressSaveDTO dto, Restaurant restaurant) {
        RestaurantAdress adress = new RestaurantAdress();
        adress.setRestaurant(restaurant);
        adress.setCep(dto.cep());
        adress.setState(dto.state());
        adress.setCity(dto.city());
        adress.setStreet(dto.street());
        adress.setNumber(dto.number());
        adress.setDistrict(dto.district());
        adress.setComplement(dto.complement());
        adress.setReference(dto.reference());
        return adress;
    }

    public static RestaurantAdressDTO toDTO(RestaurantAdress adress) {
        return new RestaurantAdressDTO(
                adress.getId().toString(),
                adress.getRestaurant().getId(),
                adress.getCep(),
                adress.getState(),
                adress.getCity(),
                adress.getStreet(),
                adress.getNumber(),
                adress.getDistrict(),
                adress.getComplement(),
                adress.getReference()
        );
    }
}


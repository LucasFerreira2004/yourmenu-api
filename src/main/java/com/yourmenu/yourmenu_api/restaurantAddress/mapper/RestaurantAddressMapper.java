package com.yourmenu.yourmenu_api.restaurantAddress.mapper;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurantAddress.RestaurantAddress;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressSaveDTO;

public class RestaurantAddressMapper {

    public static RestaurantAddress toEntity(RestaurantAddressSaveDTO dto, Restaurant restaurant) {
        RestaurantAddress adress = new RestaurantAddress();
        adress.setRestaurant(restaurant);
        adress.setCep(dto.cep().replace("-", ""));
        adress.setState(dto.state());
        adress.setCity(dto.city());
        adress.setStreet(dto.street());
        adress.setNumber(dto.number());
        adress.setDistrict(dto.district());
        adress.setComplement(dto.complement());
        adress.setReference(dto.reference());
        return adress;
    }

    public static RestaurantAddressDTO toDTO(RestaurantAddress adress) {
        return new RestaurantAddressDTO(
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


package com.yourmenu.yourmenu_api.restaurantAddress;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressSaveDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.mapper.RestaurantAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantAddressService {
    @Autowired
    private RestaurantAdressRepository restaurantAdressRepository;

    @Autowired
    private RestaurantAddressValidateService restaurantAddressValidateService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transient
    public RestaurantAddressDTO save(RestaurantAddressSaveDTO dto, String adminId){
        restaurantAddressValidateService.validateAllToSave(dto.restaurantId(), adminId);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
        RestaurantAddress restaurantAddress = RestaurantAddressMapper.toEntity(dto, restaurant);
        restaurantAdressRepository.save(restaurantAddress);
        return RestaurantAddressMapper.toDTO(restaurantAddress);
    }

    public RestaurantAddressDTO getRestaurantAdress(String restaurantId){
        restaurantAddressValidateService.validateAdressExists(restaurantId);
        RestaurantAddress adress = restaurantAdressRepository.findByRestaurantId(restaurantId);
        return RestaurantAddressMapper.toDTO(adress);
    }

    public RestaurantAddressDTO update(RestaurantAddressSaveDTO dto, String adminId) {
        restaurantAddressValidateService.validateAllToUpdate(dto.restaurantId(), adminId);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
        RestaurantAddress address = RestaurantAddressMapper.toEntity(dto, restaurant);
        return RestaurantAddressMapper.toDTO(restaurantAdressRepository.save(address));
    }
}

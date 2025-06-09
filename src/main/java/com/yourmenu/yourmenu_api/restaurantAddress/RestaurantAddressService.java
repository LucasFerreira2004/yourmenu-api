package com.yourmenu.yourmenu_api.restaurantAddress;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.dto.RestaurantAddressSaveDTO;
import com.yourmenu.yourmenu_api.restaurantAddress.mapper.RestaurantAddressMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantAddressService {
    @Autowired
    private RestaurantAddressRepository restaurantAddressRepository;

    @Autowired
    private RestaurantAddressValidateService restaurantAddressValidateService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantAddressDTO save(RestaurantAddressSaveDTO dto, String adminId){
        restaurantAddressValidateService.validateAllToSave(dto.restaurantId(), adminId);
        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
        RestaurantAddress restaurantAddress = RestaurantAddressMapper.toEntity(dto, restaurant);
        restaurantAddressRepository.save(restaurantAddress);
        return RestaurantAddressMapper.toDTO(restaurantAddress);
    }

    public RestaurantAddressDTO getRestaurantAdress(String restaurantId){
        restaurantAddressValidateService.validateAdressExists(restaurantId);
        RestaurantAddress adress = restaurantAddressRepository.findByRestaurantId(restaurantId);
        return RestaurantAddressMapper.toDTO(adress);
    }

    @Transactional
    public RestaurantAddressDTO update(RestaurantAddressSaveDTO dto, String adminId) {
        restaurantAddressValidateService.validateAllToUpdate(dto.restaurantId(), adminId);
        try {
            RestaurantAddress oldAdress = restaurantAddressRepository.findByRestaurantId(dto.restaurantId());
            Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
            RestaurantAddress newAddress = RestaurantAddressMapper.toEntity(dto, restaurant);
            newAddress.setId(oldAdress.getId());
            return RestaurantAddressMapper.toDTO(restaurantAddressRepository.save(newAddress));
        }catch(Exception e){
            e.printStackTrace();
        }

        Restaurant restaurant = restaurantRepository.findById(dto.restaurantId()).orElseThrow(() -> new RestaurantNotFoundException(dto.restaurantId()));
        RestaurantAddress address = RestaurantAddressMapper.toEntity(dto, restaurant);
        return RestaurantAddressMapper.toDTO(restaurantAddressRepository.save(address));
    }
}

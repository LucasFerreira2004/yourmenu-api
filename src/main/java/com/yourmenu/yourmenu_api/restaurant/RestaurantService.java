package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.AdministratorService;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.mapper.RestaurantMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AdministratorService administratorService;

    @Autowired
    private RestaurantMapper restaurantMapper;

    @Autowired
    private RestaurantSlugService restaurantSlugService;

    @Autowired
    private RestaurantValidateService restaurantValidateService;

    @Transient
    public RestaurantDTO save(RestaurantSaveDTO dto, String adminId) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurant.setIsOpen(false);
        restaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));
        restaurant.setAdministrator(administratorService.findByid(adminId));
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    @Transient
    public RestaurantDTO openClose(@Valid OpenDTO dto, String adminId) {
        Restaurant restaurant = restaurantRepository.findBySlug(dto.restaurantSlug());
        restaurantValidateService.validateToUpdate(restaurant, adminId, dto.restaurantSlug());
        restaurant.setIsOpen(dto.isOpen());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    public RestaurantDTO update(@Valid RestaurantSaveDTO dto, String slug,  String adminId) {
        Restaurant restaurant = restaurantRepository.findBySlug(slug);
        restaurantValidateService.validateToUpdate(restaurant, adminId, slug);
        Restaurant updatedRestaurant = restaurantMapper.toEntity(dto);
        if (restaurant.getSlug().equals(slug) {}
        updatedRestaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));

    }

    private void conditionalGenerateSlug(Restaurant restaurant, RestaurantDTO dto){

    }
}

package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.AdministratorService;
import com.yourmenu.yourmenu_api.businessHours.services.CreateBusinessHoursService;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.mapper.RestaurantMapper;
import com.yourmenu.yourmenu_api.shared.awss3.S3Service;
import com.yourmenu.yourmenu_api.shared.utils.SlugFormater;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.beans.Transient;
import java.util.List;

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

    @Autowired
    CreateBusinessHoursService createBusinessHoursService;

    @Autowired
    private S3Service s3Service;

    @Transactional
    public RestaurantDTO save(RestaurantSaveDTO dto, MultipartFile profilePictureUrl, MultipartFile bannerPictureUrl, String adminId) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurantValidateService.validateAllToSave(adminId);
        restaurant.setIsOpen(false);
        restaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));
        restaurant.setAdministrator(administratorService.findByid(adminId));

        if(profilePictureUrl != null)
            restaurant.setProfilePicUrl(s3Service.uploadFile(profilePictureUrl));
        if(bannerPictureUrl != null)
            restaurant.setBannerPicUrl(s3Service.uploadFile(bannerPictureUrl));

        restaurantRepository.save(restaurant);
        createBusinessHoursService.execute(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    @Transactional
    public RestaurantDTO openClose(@Valid OpenDTO dto, String restaurantId, String adminId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantValidateService.validateAllToUpdate(restaurant, adminId);
        restaurant.setIsOpen(dto.isOpen());
        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    @Transactional
    public RestaurantDTO update(
            @Valid RestaurantSaveDTO dto,
            String restaurantId,
            MultipartFile profilePictureUrl,
            MultipartFile bannerPictureUrl,
            String adminId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantValidateService.validateAllToUpdate(restaurant, adminId);
        updateRestaurantData(restaurant, dto, profilePictureUrl, bannerPictureUrl);

        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    private boolean shouldGenerateSlug(Restaurant restaurant, String dtoRestaurantName){
        String normmalizedRestaurantName = SlugFormater.normalize(restaurant.getName());
        String normalizedDtoName = SlugFormater.normalize(dtoRestaurantName);
        if (normmalizedRestaurantName.equals(normalizedDtoName))
            return false;
        return true;
    }

    private Restaurant updateRestaurantData(
            Restaurant restaurant,
            RestaurantSaveDTO dto,
            MultipartFile profilePictureUrl,
            MultipartFile bannerPictureUrl) {
        if(shouldGenerateSlug(restaurant, dto.name()))
            restaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));
        restaurant.setName(dto.name());
        restaurant.setDeliveryTimeMin(dto.deliveryTimeMin());
        restaurant.setDeliveryTimeMax(dto.deliveryTimeMax());

        if(profilePictureUrl != null)
            restaurant.setProfilePicUrl(s3Service.uploadFile(profilePictureUrl));
        if(bannerPictureUrl != null)
            restaurant.setBannerPicUrl(s3Service.uploadFile(bannerPictureUrl));
        if(dto.isOpen() != null)
            restaurant.setIsOpen(dto.isOpen());
        return restaurant;
    }

    public List<RestaurantDTO> findAllByLoggedUser(String adminId) {
        List<Restaurant> restaurants = restaurantRepository.findAllByAdministratorId(adminId);
        return restaurants.stream().map(restaurant -> restaurantMapper.toDTO(restaurant)).toList();
    }

    public RestaurantDTO findById(String restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantValidateService.validateRestaurantExists(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    @Transactional
    public void delete(String restaurantId, String adminId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        restaurantValidateService.validateAllToUpdate(restaurant, adminId);
        restaurantRepository.delete(restaurant);
    }
}
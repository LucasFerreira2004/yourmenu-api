package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.AdministratorService;
import com.yourmenu.yourmenu_api.businessHours.services.CreateBusinessHoursService;
import com.yourmenu.yourmenu_api.restaurant.dto.OpenDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantDTO;
import com.yourmenu.yourmenu_api.restaurant.dto.RestaurantSaveDTO;
import com.yourmenu.yourmenu_api.restaurant.mapper.RestaurantMapper;
import com.yourmenu.yourmenu_api.shared.awss3.ImageDefaultsProperties;
import com.yourmenu.yourmenu_api.shared.awss3.S3Service;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import com.yourmenu.yourmenu_api.shared.utils.SlugFormater;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private ImageDefaultsProperties imageDefaultsProperties;

    @Transactional
    public RestaurantDTO save(RestaurantSaveDTO dto, MultipartFile profilePictureUrl, MultipartFile bannerPictureUrl, String adminId) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        restaurantValidateService.validateAllToSave(adminId);
        restaurant.setIsOpen(false);
        restaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));
        restaurant.setAdministrator(administratorService.findByid(adminId));

        adicionarImagens(restaurant, profilePictureUrl, bannerPictureUrl);

        restaurantRepository.save(restaurant);
        createBusinessHoursService.execute(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    @Transactional
    public RestaurantDTO openClose(@Valid OpenDTO dto, String restaurantId, String adminId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Restaurant not found with id: " + restaurantId));
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
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Restaurant not found with id: " + restaurantId));
        restaurantValidateService.validateAllToUpdate(restaurant, adminId);

        updateRestaurantData(restaurant, dto, profilePictureUrl, bannerPictureUrl);

        restaurantRepository.save(restaurant);
        return restaurantMapper.toDTO(restaurant);
    }

    public RestaurantDTO findAllByLoggedUser(String adminId) {
        Restaurant restaurantResponse = restaurantRepository.findByAdministratorId(adminId);
        return restaurantMapper.toDTO(restaurantResponse);
    }

    public RestaurantDTO findById(String restaurantId) {
        return restaurantRepository.findById(restaurantId)
            .map(restaurant -> {
                restaurantValidateService.validateRestaurantExists(restaurant);
                return restaurantMapper.toDTO(restaurant);
            })
            .orElseThrow(() -> new ResourceNotFoundException("id", "Restaurant not found with id: " + restaurantId));
    }

    @Transactional
    public void delete(String restaurantId, String adminId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Restaurant not found with id: " + restaurantId));
        restaurantValidateService.validateAllToUpdate(restaurant, adminId);
        restaurantRepository.delete(restaurant);
    }

    public RestaurantDTO updateImageProfileRestaurant(String restaurantId, String adminId, MultipartFile imagem) {
        Restaurant restaurant = findAndValidatePermission(restaurantId, adminId);

        String novaUrl = (imagem != null && !imagem.isEmpty())
                ? s3Service.uploadFile(imagem)
                : imageDefaultsProperties.getDefaultVisualDish();

        restaurant.setProfilePicUrl(novaUrl);
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO deleteImageProfileRestaurant(String restaurantId, String adminId) {
        Restaurant restaurant = findAndValidatePermission(restaurantId, adminId);

        restaurant.setProfilePicUrl(imageDefaultsProperties.getDefaultVisualDish());
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO deleteImageBannerRestaurant(String restaurantId, String adminId) {
        Restaurant restaurant = findAndValidatePermission(restaurantId, adminId);

        restaurant.setProfilePicUrl(imageDefaultsProperties.getDefaultVisualDish());
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    public RestaurantDTO updateImageBannerRestaurant(String restaurantId, String adminId, MultipartFile imagem) {
        Restaurant restaurant = findAndValidatePermission(restaurantId, adminId);

        String novaUrl = (imagem != null && !imagem.isEmpty())
                ? s3Service.uploadFile(imagem)
                : imageDefaultsProperties.getDefaultVisualDish();

        restaurant.setBannerPicUrl(novaUrl);
        return restaurantMapper.toDTO(restaurantRepository.save(restaurant));
    }

    private boolean shouldGenerateSlug(Restaurant restaurant, String dtoRestaurantName){
        String normmalizedRestaurantName = SlugFormater.normalize(restaurant.getName());
        String normalizedDtoName = SlugFormater.normalize(dtoRestaurantName);
        return (normmalizedRestaurantName.equals(normalizedDtoName));
    }

    private void updateRestaurantData(
            Restaurant restaurant,
            RestaurantSaveDTO dto,
            MultipartFile profilePictureUrl,
            MultipartFile bannerPictureUrl) {
        if(shouldGenerateSlug(restaurant, dto.name()))
            restaurant.setSlug(restaurantSlugService.generateSlug(dto.name()));
        restaurant.setName(dto.name());
        restaurant.setDeliveryTimeMin(dto.deliveryTimeMin());
        restaurant.setDeliveryTimeMax(dto.deliveryTimeMax());

        if (profilePictureUrl != null && !profilePictureUrl.isEmpty())
            restaurant.setProfilePicUrl(s3Service.uploadFile(profilePictureUrl));
        if (bannerPictureUrl != null && !bannerPictureUrl.isEmpty())
            restaurant.setBannerPicUrl(s3Service.uploadFile(bannerPictureUrl));
        if(dto.isOpen() != null)
            restaurant.setIsOpen(dto.isOpen());
    }

    private void adicionarImagens(Restaurant restaurant, MultipartFile profilePictureUrl, MultipartFile bannerPictureUrl) {
        if(profilePictureUrl != null && !profilePictureUrl.isEmpty())
            restaurant.setProfilePicUrl(s3Service.uploadFile(profilePictureUrl));
        else
            restaurant.setProfilePicUrl(imageDefaultsProperties.getDefaultIconRestaurant());

        if(bannerPictureUrl != null && !bannerPictureUrl.isEmpty())
            restaurant.setBannerPicUrl(s3Service.uploadFile(bannerPictureUrl));
        else
            restaurant.setBannerPicUrl(imageDefaultsProperties.getDefaultCapaRestaurant());
    }

    private Restaurant findAndValidatePermission(String restaurantId, String adminId) {
        Restaurant restaurant = restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("id", "Restaurante nao encontrado como id " + restaurantId));
        restaurantValidateService.validateAdministratorCanEditOrViewRestaurant(restaurantId, adminId);

        return restaurant;
    }
}

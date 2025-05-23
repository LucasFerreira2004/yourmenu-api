package com.yourmenu.yourmenu_api.businessHours.services;

import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.BusinessHoursRepository;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.businessHours.mapper.BusinessHoursMapper;
import com.yourmenu.yourmenu_api.restaurant.RestaurantRepository;
import com.yourmenu.yourmenu_api.restaurant.exception.RestaurantNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

@Service
public class ListingAllBusinessHoursService {

    @Autowired
    BusinessHoursRepository businessHoursRepository;

    @Autowired
    BusinessHoursMapper businessHoursMapper;

    @Autowired
    RestaurantRepository restaurantRepository;

    //versão 2 - pretendo retornar apenas as que possuem horario, pois as nulas significam que não serão abertas no dia
    public List<BusinessHoursDTO> execute(String restaurantId) {

        this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException("id_restaurant"));

        List<BusinessHours> lista = businessHoursRepository.findAllByRestaurantId(restaurantId);

        TreeSet<BusinessHours> businessHoursSet = new TreeSet<>( //para ordenar os objetos de acordo com a ordem natural do Enum
                Comparator.comparing(BusinessHours::getWeekday)
        );

        businessHoursSet.addAll(lista);

        return businessHoursSet.stream()
                .map(businessHoursMapper::toDTO)
                .toList();
    }
}
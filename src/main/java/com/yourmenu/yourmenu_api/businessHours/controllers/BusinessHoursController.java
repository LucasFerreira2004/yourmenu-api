package com.yourmenu.yourmenu_api.businessHours.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.businessHours.services.ListingBusinessHoursUseCase;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/business-hours")
public class BusinessHoursController {


    @Autowired
    ListingBusinessHoursUseCase listingBusinessHoursUseCase;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<BusinessHoursDTO>> findAllByRestaurant(@PathVariable String restaurantId, @CurrentUser Administrator currentUser) {
        System.out.println("prestou"); //depuração
        List<BusinessHoursDTO> response = listingBusinessHoursUseCase.execute(restaurantId);
        return ResponseEntity.ok(response);
    }

}

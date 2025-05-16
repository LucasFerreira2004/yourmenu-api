package com.yourmenu.yourmenu_api.businessHours.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursPeriodDTO;
import com.yourmenu.yourmenu_api.businessHours.services.ListingAllBusinessHoursUseCase;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business-hours")
public class BusinessHoursController {


    @Autowired
    ListingAllBusinessHoursUseCase listingAllBusinessHoursUseCase;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<BusinessHoursDTO>> findAllByRestaurant(@PathVariable String restaurantId, @CurrentUser Administrator currentUser) {
        List<BusinessHoursDTO> response = listingAllBusinessHoursUseCase.execute(restaurantId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<List<BusinessHoursDTO>> update(@PathVariable String restaurantId, @CurrentUser Administrator currentUser, @RequestBody BusinessHoursPeriodDTO businessHoursPeriodDTO) {
        System.out.println(businessHoursPeriodDTO);
        System.out.println("prestou");
        return ResponseEntity.noContent().build();
    }

}

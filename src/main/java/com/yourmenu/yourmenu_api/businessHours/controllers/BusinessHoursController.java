package com.yourmenu.yourmenu_api.businessHours.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursDTO;
import com.yourmenu.yourmenu_api.businessHours.dto.BusinessHoursPeriodDTO;
import com.yourmenu.yourmenu_api.businessHours.services.ListingAllBusinessHoursService;
import com.yourmenu.yourmenu_api.businessHours.services.RegisterUptimeUseCase;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business-hours")
public class BusinessHoursController {


    @Autowired
    ListingAllBusinessHoursService listingAllBusinessHoursService;

    @Autowired
    RegisterUptimeUseCase registerUptimeUseCase;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<BusinessHoursDTO>> findAllByRestaurant(@PathVariable String restaurantId, @CurrentUser Administrator currentUser) {
        List<BusinessHoursDTO> response = listingAllBusinessHoursService.execute(restaurantId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<List<BusinessHoursDTO>> update(@PathVariable String restaurantId, @CurrentUser Administrator currentUser, @Valid  @RequestBody BusinessHoursPeriodDTO businessHoursPeriodDTO) {

        registerUptimeUseCase.execute(restaurantId, businessHoursPeriodDTO);

        return ResponseEntity.noContent().build();
    }

}

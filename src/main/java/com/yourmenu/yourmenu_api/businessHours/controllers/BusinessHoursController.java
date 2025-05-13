package com.yourmenu.yourmenu_api.businessHours.controllers;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.businessHours.BusinessHours;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/business-hours")
public class BusinessHoursController {


    @GetMapping
    public List<BusinessHours> findAllByRestaurant(@CurrentUser Administrator currentUser) {
        System.out.println("prestou"); //depuração
        return null;
    }

}

package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.Controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish/{dishId}/prices")
public class DishSizeOptionController {

    @GetMapping()
    public void verURL(){
        System.out.println("prestou");
    }
}

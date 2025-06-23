package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Controllers;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services.CreateSizeOptionService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services.ListingAllSizeOptionsService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sizeoptions")
public class SizeOptionsController {

    @Autowired
    private ListingAllSizeOptionsService listingAllSizeOptionsService;

    @GetMapping
    public ResponseEntity<List<SizeOptionDTO>> getAllSizeOptions(){
        List<SizeOptionDTO> response = listingAllSizeOptionsService.execute();
        return ResponseEntity.ok(response);
    }
}

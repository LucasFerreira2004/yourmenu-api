package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.controllers;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.services.ListingAllSizeOptionsService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.services.SearchSizeOptionService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sizeoptions")
public class SizeOptionsController {

    @Autowired
    private ListingAllSizeOptionsService listingAllSizeOptionsService;

    @Autowired
    private SearchSizeOptionService searchSizeOptionService;

    @GetMapping
    public ResponseEntity<List<SizeOptionDTO>> getAllSizeOptions(){
        List<SizeOptionDTO> response = listingAllSizeOptionsService.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{sizeOptionId}")
    public ResponseEntity<SizeOptionDTO> getSizeOptionsById(@PathVariable Long sizeOptionId){
        SizeOptionDTO response = searchSizeOptionService.execute(sizeOptionId);
        return ResponseEntity.ok(response);
    }
}

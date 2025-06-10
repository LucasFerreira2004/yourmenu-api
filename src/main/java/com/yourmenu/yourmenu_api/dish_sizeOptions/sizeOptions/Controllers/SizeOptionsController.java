package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Controllers;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services.CreateSizeOptionService;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sizeoptions")
public class SizeOptionsController {

    @Autowired
    CreateSizeOptionService createSizeOptionService;

    @PostMapping
    public ResponseEntity<List<SizeOptionDTO>> save(){
        List<SizeOptionDTO> response = createSizeOptionService.execute();
        return ResponseEntity.ok(response);
    }
}

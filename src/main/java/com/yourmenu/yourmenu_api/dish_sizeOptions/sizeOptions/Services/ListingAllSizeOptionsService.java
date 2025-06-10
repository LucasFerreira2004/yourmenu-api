package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingAllSizeOptionsService {

    @Autowired
    SizeOptionRepository sizeOptionRepository;

    public List<SizeOption> execute(){
        return sizeOptionRepository.findAll();
    }
}

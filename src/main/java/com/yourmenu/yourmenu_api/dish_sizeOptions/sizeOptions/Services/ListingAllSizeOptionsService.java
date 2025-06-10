package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Mappers.SizeOptionsMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListingAllSizeOptionsService {

    @Autowired
    SizeOptionRepository sizeOptionRepository;

    @Autowired
    SizeOptionsMapper sizeOptionsMapper;

    public List<SizeOptionDTO> execute(){
        List<SizeOption> sizeOptions = sizeOptionRepository.findAll();

        return sizeOptions.stream()
                .map(sizeOptionsMapper::toDTO)
                .collect(Collectors.toList());
    }
}

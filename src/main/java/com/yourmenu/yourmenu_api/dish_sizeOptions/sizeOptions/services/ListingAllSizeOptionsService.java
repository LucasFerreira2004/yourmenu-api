package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.mappers.SizeOptionsMapper;
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
    private SizeOptionRepository sizeOptionRepository;

    @Autowired
    private SizeOptionsMapper sizeOptionsMapper;

    public List<SizeOptionDTO> execute(){
        List<SizeOption> sizeOptions = sizeOptionRepository.findAll();

        return sizeOptions.stream()
                .map(sizeOptionsMapper::toDTO)
                .collect(Collectors.toList());
    }
}

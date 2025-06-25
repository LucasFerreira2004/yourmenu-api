package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.exceptions.SizeOptionNotFound;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Mappers.SizeOptionsMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchSizeOptionService {
    @Autowired
    SizeOptionRepository sizeOptionRepository;

    @Autowired
    SizeOptionsMapper sizeOptionsMapper;

    public SizeOptionDTO execute(Long sizeOptionId) {

        SizeOption sizeOption = sizeOptionRepository.findById(sizeOptionId)
                .orElseThrow(() -> new SizeOptionNotFound("sizeOptionId", "Opção de tamanho com ID " + sizeOptionId + " não existe."));

        return sizeOptionsMapper.toDTO(sizeOption);
    }
}

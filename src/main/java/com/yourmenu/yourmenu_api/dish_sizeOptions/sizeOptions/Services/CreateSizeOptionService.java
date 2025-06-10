package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.Mappers.SizeOptionsMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.MeasureUnit;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.dto.SizeOptionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateSizeOptionService {

    @Autowired
    SizeOptionRepository sizeOptionRepository;

    @Autowired
    SizeOptionsMapper sizeOptionsMapper;

    //DEIXAR PRONTA PARA INICIALIZAR COM TODOS OS TAMANHOS CORRETAMENTE
    public List<SizeOptionDTO> execute() {
        List<SizeOptionDTO> result = new ArrayList<>();
        for (int i = 0; i < MeasureUnit.values().length; i++) {
            if (i < 3) {
                SizeOption sizeOption = new SizeOption();
                sizeOption.setMeasureUnit(MeasureUnit.values()[i]);
                sizeOption.setMagnitude(null);
                sizeOptionRepository.save(sizeOption);
                result.add(sizeOptionsMapper.toDTO(sizeOption));
                continue;
            }
            for (int j = 0; j < 4; j++) {
                SizeOption sizeOption = new SizeOption();
                int mag = 50 * (j + 1);
                sizeOption.setMeasureUnit(MeasureUnit.values()[i]);
                sizeOption.setMagnitude(String.valueOf(mag));
                sizeOptionRepository.save(sizeOption);
                result.add(sizeOptionsMapper.toDTO(sizeOption));
            }
        }
        return result;
    }
}

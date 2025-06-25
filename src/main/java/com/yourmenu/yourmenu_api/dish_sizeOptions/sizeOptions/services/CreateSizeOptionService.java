package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.mappers.SizeOptionsMapper;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.MeasureUnit;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateSizeOptionService {

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    @Autowired
    private SizeOptionsMapper sizeOptionsMapper;

    //DEIXAR PRONTA PARA INICIALIZAR COM TODOS OS TAMANHOS CORRETAMENTE - PERGUNTAR QUAIS TAMANHOS ADICIONAR
    @PostConstruct
    private void init() {
        if (sizeOptionRepository.count() > 0) return; // Evita duplicar ao reiniciar

        for (int i = 0; i < MeasureUnit.values().length; i++) {
            if (i < 3) {
                SizeOption sizeOption = new SizeOption();
                sizeOption.setMeasureUnit(MeasureUnit.values()[i]);
                sizeOption.setMagnitude(null);
                sizeOptionRepository.save(sizeOption);
                continue;
            }
            for (int j = 0; j < 4; j++) {
                SizeOption sizeOption = new SizeOption();
                int mag = 50 * (j + 1);
                sizeOption.setMeasureUnit(MeasureUnit.values()[i]);
                sizeOption.setMagnitude(String.valueOf(mag));
                sizeOptionRepository.save(sizeOption);
            }
        }
    }
}

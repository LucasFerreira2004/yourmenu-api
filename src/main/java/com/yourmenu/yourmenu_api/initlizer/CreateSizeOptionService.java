package com.yourmenu.yourmenu_api.initlizer;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.MeasureUnit;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CreateSizeOptionService {

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    @PostConstruct
    private void init() {
        if (sizeOptionRepository.count() > 0) return;

        // Tamanhos simbólicos: PEQUENO, MEDIO, GRANDE
        for (int i = 0; i < 3; i++) {
            SizeOption sizeOption = new SizeOption();
            sizeOption.setMeasureUnit(MeasureUnit.values()[i]);
            sizeOption.setMagnitude(null);
            sizeOptionRepository.save(sizeOption);
        }

        // Tamanhos com magnitude realistas
        Map<MeasureUnit, List<String>> medidasComuns = Map.of(
                MeasureUnit.MILILITRO, List.of("200", "300", "350", "500", "600", "1000"),
                MeasureUnit.LITRE, List.of("1", "1.5", "2", "5"),
                MeasureUnit.GRAMA, List.of("50", "100", "150", "200", "300", "500"),
                MeasureUnit.KILOGRAMA, List.of("0.5", "1", "1.5", "2")
        );

        for (Map.Entry<MeasureUnit, List<String>> entry : medidasComuns.entrySet()) {
            MeasureUnit unidade = entry.getKey();
            for (String magnitude : entry.getValue()) {
                SizeOption sizeOption = new SizeOption();
                sizeOption.setMeasureUnit(unidade);
                sizeOption.setMagnitude(magnitude);
                sizeOptionRepository.save(sizeOption);
            }
        }
        //garantir a inicialização correta

    }
}

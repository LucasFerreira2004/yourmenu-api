package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.exceptions.SizeOptionNotFound;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UpdateAssociationsService {

    @Autowired
    private DishSizeOptionRepository dishSizeOptionRepository;

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    @Transactional
    public void execute(List<SizeOptionPriceDTO> dto, Dish dish) {
        long dishId = dish.getId();

        List<DishSizeOption> currentAssociations = dishSizeOptionRepository.findAllByDishId(dishId);
        List<DishSizeOption> newAssociations = new ArrayList<>();

        for (SizeOptionPriceDTO sizeOptionPriceDTO : dto) {

            Long sizeOptionId = sizeOptionPriceDTO.sizeOptionId();

            SizeOption sizeOption = sizeOptionRepository.findById(sizeOptionId)
                    .orElseThrow(() -> new SizeOptionNotFound("sizeOptionId", "Opção de tamanho com ID " + sizeOptionId + " não existe."));

            Optional<DishSizeOption> optional = dishSizeOptionRepository
                    .findByDishIdAndSizeOptionId(dishId, sizeOptionId);


            if (optional.isPresent()) {
                DishSizeOption existing = optional.get();
                existing.setPrice(sizeOptionPriceDTO.price());
                dishSizeOptionRepository.save(existing);
                newAssociations.add(existing);
            } else {
                DishSizeOption newAssociation = new DishSizeOption();
                newAssociation.setDish(dish);
                newAssociation.setSizeOption(sizeOption);
                newAssociation.setPrice(sizeOptionPriceDTO.price());
                dishSizeOptionRepository.save(newAssociation);
                newAssociations.add(newAssociation);
            }
        }

        //remover os que não foram usados (delete automatico)
        for(int i = 0; i < currentAssociations.size(); i++) {
            if(!newAssociations.contains(currentAssociations.get(i))) {
                dishSizeOptionRepository.deleteByIdNative(currentAssociations.get(i).getId());
            }
        }
    }
}


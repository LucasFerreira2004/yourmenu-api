package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.Services;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOptionRepository;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.dto.SizeOptionPriceDTO;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOptionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UpdateAssociationsService {

    @Autowired
    private DishSizeOptionRepository dishSizeOptionRepository;

    @Autowired
    private SizeOptionRepository sizeOptionRepository;

    @Transactional
    public void execute(List<SizeOptionPriceDTO> dto, Dish dish) {
        long dishId = dish.getId();

        // Guarda os IDs vindos do DTO
        Set<Long> dtoIds = dto.stream()
                .map(SizeOptionPriceDTO::sizeOptionId)
                .collect(Collectors.toSet());

        // Busca todos os registros atuais do prato
        List<DishSizeOption> currentAssociations = dishSizeOptionRepository.findAllByDishId(dishId);

        // Atualiza ou cria
        for (SizeOptionPriceDTO sizeOptionPriceDTO : dto) {
            Long sizeOptionId = sizeOptionPriceDTO.sizeOptionId();

            Optional<DishSizeOption> optional = dishSizeOptionRepository
                    .findByDishIdAndSizeOptionId(dishId, sizeOptionId);

            if (optional.isPresent()) {
                DishSizeOption existing = optional.get();
                existing.setPrice(sizeOptionPriceDTO.price());
                dishSizeOptionRepository.save(existing);
            } else {
                SizeOption sizeOption = sizeOptionRepository.findById(sizeOptionId)
                        .orElseThrow(() -> new RuntimeException("SizeOption não encontrada: " + sizeOptionId));

                DishSizeOption newAssociation = new DishSizeOption();
                newAssociation.setDish(dish);
                newAssociation.setSizeOption(sizeOption);
                newAssociation.setPrice(sizeOptionPriceDTO.price());
                dishSizeOptionRepository.save(newAssociation);
            }
        }

        // Apaga os registros que não estão mais no DTO
        for (DishSizeOption existing : currentAssociations) {
            Long existingSizeOptionId = existing.getSizeOption().getId();
            if (!dtoIds.contains(existingSizeOptionId)) {
                dishSizeOptionRepository.delete(existing);
            }
        }
    }
}


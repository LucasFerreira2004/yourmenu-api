package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishSizeOptionRepository extends JpaRepository<DishSizeOption, Long> {
    Optional<DishSizeOption> findByDishIdAndSizeOptionId(Long dishId, Long sizeOptionId);
    List<DishSizeOption> findAllByDishId(Long dishId);
}

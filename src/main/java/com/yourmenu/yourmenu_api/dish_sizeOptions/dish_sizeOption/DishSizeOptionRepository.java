package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DishSizeOptionRepository extends JpaRepository<DishSizeOption, Long> {
    Optional<DishSizeOption> findByDishIdAndSizeOptionId(Long dishId, Long sizeOptionId);
    List<DishSizeOption> findAllByDishId(Long dishId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM dish_size_option WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(Long id);
}

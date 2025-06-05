package com.yourmenu.yourmenu_api.dish_sizeOptions.dish;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    @Query(nativeQuery = true, value = """
        select * from dish where name = :name and restaurant_id = :restaurant_id;
    """)
    Dish findByNameAndRestaurant(String name, String restaurant_id);

    @Query(nativeQuery = true, value = """
        select * from dish where category_id = :categoryId;
    """)
    List<Dish> findAllByCategoryId(Long categoryId);
}

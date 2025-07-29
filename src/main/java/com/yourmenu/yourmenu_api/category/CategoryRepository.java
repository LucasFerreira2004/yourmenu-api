package com.yourmenu.yourmenu_api.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(nativeQuery = true, value = """
        select * from category where name = :name and restaurant_id = :restaurantId;
    """)
    Category findByNameAndRestaurantId(String name, String restaurantId);

    @Query(nativeQuery = true, value = """
        select * from category where restaurant_id = :restaurantId;
    """)
    List<Category> findAllByRestaurantId(String restaurantId);
    
    @Modifying
    @Query("DELETE FROM Category c WHERE c.restaurant.id = :restaurantId")
    void deleteAllByRestaurantId(@Param("restaurantId") String restaurantId);
}

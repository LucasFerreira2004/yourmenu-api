package com.yourmenu.yourmenu_api.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(nativeQuery = true, value = """
        select * from category where name = :name and restaurant_id = :restaurantId;
    """)
    public Category findByNameAndRestaurantId(String name, String restaurantId);
}

package com.yourmenu.yourmenu_api.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    @Query(nativeQuery = true, value="""
    select * from restaurant where slug = :slug
""")
    Restaurant findBySlug(String slug);
}

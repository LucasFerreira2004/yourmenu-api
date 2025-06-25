package com.yourmenu.yourmenu_api.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    @Query(nativeQuery = true, value="""
    select * from restaurant where slug = :slug
""")
    Restaurant findBySlug(String slug);
@Query(nativeQuery = true, value = """
    select * from restaurant where administrator_id = :adminId
""")
    List<Restaurant> findAllByAdministratorId(String adminId);

    Restaurant findByid(String id);

    @Query(nativeQuery = true, value = """
    select * from restaurant where administrator_id = :adminId
    """)
    Restaurant findByAdministratorId(String adminId);
}
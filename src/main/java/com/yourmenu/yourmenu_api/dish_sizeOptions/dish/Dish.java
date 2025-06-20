package com.yourmenu.yourmenu_api.dish_sizeOptions.dish;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"restaurant_id", "name"})
        }
)
@Entity
@Data
@NoArgsConstructor
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    public Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

    @Column(nullable = false)
    public String name;

    @Column(columnDefinition = "text")
    public String description;

    @Column(nullable = false)
    public Boolean isAvailable;

    public String imageUrl;
}

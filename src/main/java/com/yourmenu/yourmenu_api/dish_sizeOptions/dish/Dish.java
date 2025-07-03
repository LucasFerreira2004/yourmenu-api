package com.yourmenu.yourmenu_api.dish_sizeOptions.dish;

import com.yourmenu.yourmenu_api.category.Category;
import com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption.DishSizeOption;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DishSizeOption> sizeOptions;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Boolean isAvailable;

    private String imageUrl;
}

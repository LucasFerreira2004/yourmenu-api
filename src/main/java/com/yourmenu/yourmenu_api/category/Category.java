package com.yourmenu.yourmenu_api.category;

import com.yourmenu.yourmenu_api.dish.Dish;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    //observar se dará o erro de referência cíclica de toString().
    @OneToMany
    private List<Dish> dishes;

    //private Boolean suportsComposite;
    //private Integer maxNumberComposites;
    //private Boolean suportsAddons;
    //private Integer maxNumberAddons;
}

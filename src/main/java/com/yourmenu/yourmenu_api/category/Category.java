package com.yourmenu.yourmenu_api.category;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
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

    @JoinColumn(nullable = false)
    private String name;

    //private Boolean suportsComposite;
    //private Integer maxNumberComposites;
    //private Boolean suportsAddons;
    //private Integer maxNumberAddons;
}

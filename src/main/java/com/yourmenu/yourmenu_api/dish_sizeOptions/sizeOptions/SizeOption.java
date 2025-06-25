package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class SizeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // não necessário pois não há mais crud tamanhos
//    @ManyToOne
//    @JoinColumn(name = "restaurant_id", nullable = false)
//    private Restaurant restaurant;

    @Column(nullable = false)
    private String value;

    //fica a critério do back fazer ou não esse campo, não sei se realmente será necessário
    //private String displayValue;

    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
}

package com.yourmenu.yourmenu_api.restaurant.restaurantAdress;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class RestaurantAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "restaurant_id", nullable = false, unique = true)
    private Restaurant restaurant;

    @Column(columnDefinition = "numeric(8)", nullable = false)
    private Integer cep;

    @Column(columnDefinition = "numeric(8)", nullable = false)
    private String state;

    @Column(columnDefinition = "text", nullable = false)
    private String city;

    @Column(columnDefinition = "text", nullable = false)
    private String street;

    private Integer number;

    @Column(columnDefinition = "text", nullable = false)
    private String district;

    @Column(columnDefinition = "text")
    private String complement;

    @Column(columnDefinition = "text")
    private String reference;
    //código inicial, creio que uma boa refatoração seria colocar enums para representar estados.
}

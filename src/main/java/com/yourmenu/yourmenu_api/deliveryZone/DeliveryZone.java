package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "delivery_zone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryZone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "zone", nullable = false)
    private String zone;

    @Column(name = "delivery_fee", nullable = false)
    private BigDecimal deliveryFee;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
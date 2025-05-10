package com.yourmenu.yourmenu_api.businessHours;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_businessHours;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    private LocalTime openingTime;

    private LocalTime closingTime;
}

package com.yourmenu.yourmenu_api.businessHours;

import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class BusinessHours {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id_businessHours;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Enumerated(EnumType.STRING)
    private Weekday weekday;

    @Column(name = "opening_time", nullable = true)
    private LocalTime openingTime = null;

    @Column(name = "closing_time", nullable = true)
    private LocalTime closingTime = null;

    private Boolean isOpen = false;
}

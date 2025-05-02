package com.yourmenu.yourmenu_api.restaurant;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(nullable = false)
    private String name;

    private Integer deliveryTimeMin;

    private Integer deliveryTimeMax;

    private Boolean isOpen;

    @Column(columnDefinition = "text")
    private String profilePicUrl;

    @Column(columnDefinition = "text")
    private String BannerPicUrl;
}

package com.yourmenu.yourmenu_api.restaurant;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"\"administrator_id\""})
        }
)
@Entity
@Data
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "administrator_id", nullable = false)
    private Administrator administrator;

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

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant")
    private List<Category> categories;

}

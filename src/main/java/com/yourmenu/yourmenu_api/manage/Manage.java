package com.yourmenu.yourmenu_api.manage;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.restaurant.Restaurant;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Manage {

    @EmbeddedId
    private ManageId id;

    @ManyToOne
    @MapsId("administratorId")
    @JoinColumn(name = "administrator_id")
    private Administrator administrator;

    @ManyToOne
    @MapsId("restaurantId")
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}


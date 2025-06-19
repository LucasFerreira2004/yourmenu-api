package com.yourmenu.yourmenu_api.order.orderItem;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import com.yourmenu.yourmenu_api.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "order_item")
@Entity
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;
}

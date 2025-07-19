package com.yourmenu.yourmenu_api.order_client;

import com.yourmenu.yourmenu_api.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.HashCodeExclude;

@Data
@Entity
@NoArgsConstructor
public class OrderClient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ToString.Exclude
    @HashCodeExclude
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, columnDefinition = "text")
    private String firstName;

    @Column(nullable = false, columnDefinition = "text")
    private String lastName;

    @Column(nullable = false, columnDefinition = "VARCHAR(11)")
    private String phone;
}

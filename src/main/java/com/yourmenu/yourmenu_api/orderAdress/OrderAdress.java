package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.deliveryZone.DeliveryZone;
import com.yourmenu.yourmenu_api.order.Order;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.HashCodeExclude;

@Entity
@Data
@Table(name = "order_adress")
public class OrderAdress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ToString.Exclude
    @HashCodeExclude
    @OneToOne()
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne()
    @JoinColumn(name = "delivery_zone_id", nullable = false)
    private DeliveryZone deliveryZone;

    @Column(name = "cep", nullable = false, length = 8)
    private Long cep;

    @Column(name = "street", nullable = false, columnDefinition = "text")
    private String street;

    @Column(name = "number", nullable = false, length = 20)
    private String number;

    @Column(name = "complement", length = 100)
    private String complement;

    @Column(name = "reference", length = 100)
    private String reference;
}

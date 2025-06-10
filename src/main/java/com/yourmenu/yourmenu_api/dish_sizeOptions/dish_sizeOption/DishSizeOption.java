package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@NoArgsConstructor
@Data
public class DishSizeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sizeOption_id", nullable = false)
    private SizeOption sizeOption;

    @Column(precision = 10, scale = 2)
    @Digits(integer = 8, fraction = 2)
    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    public void setPrice(BigDecimal price) {
        if (price != null) {
            this.price = price.setScale(2, RoundingMode.HALF_UP);
        } else {
            this.price = null;
        }
    }
}

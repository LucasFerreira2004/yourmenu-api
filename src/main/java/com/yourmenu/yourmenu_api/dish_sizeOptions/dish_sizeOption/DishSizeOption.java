package com.yourmenu.yourmenu_api.dish_sizeOptions.dish_sizeOption;

import com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions.SizeOption;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

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

    @NotNull
    @Min(0)
    private BigDecimal value;
}

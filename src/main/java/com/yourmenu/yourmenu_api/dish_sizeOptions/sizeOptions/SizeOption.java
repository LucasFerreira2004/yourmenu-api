package com.yourmenu.yourmenu_api.dish_sizeOptions.sizeOptions;

import com.yourmenu.yourmenu_api.dish_sizeOptions.dish.Dish;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SizeOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @NotBlank //So para Strings
    @Column(nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private MeasureUnit measureUnit;
    /*
    * NotNull e nullble=false se complementam pois
    * um atua no momento que passam os dados (a nível de Java)
    * e o outro, no momento que tenta armazenar algo no banco de dados
    */
}

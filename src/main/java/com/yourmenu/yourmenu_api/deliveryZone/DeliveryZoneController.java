package com.yourmenu.yourmenu_api.deliveryZone;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZoneDto;
import com.yourmenu.yourmenu_api.deliveryZone.dto.DeliveryZonePostDto;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-zone")
@Tag(name = "Delivery Zone", description = "delivery rates for different areas")
public class DeliveryZoneController {
    @Autowired
    private DeliveryZoneService deliveryZoneService;

    @GetMapping()
    @Operation(summary = "Retorna as zonas de entrega", description = "Retorna todas as zonas de entrega e seus valores")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zonas de entrega encontradas")
    })
    public ResponseEntity<List<DeliveryZoneDto>> findAll() {
        List<DeliveryZoneDto> zones = deliveryZoneService.findAll();
        return ResponseEntity.ok(zones);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna pelo id", description = "Retorna uma zona de entrega pelo id")
    @ResponseStatus(value = HttpStatus.OK)
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zonas de entrega encontradas")
    })
    public ResponseEntity<DeliveryZoneDto> findById(@PathVariable Long id) {
        DeliveryZoneDto zone = deliveryZoneService.findById(id);
        return ResponseEntity.ok(zone);
    }

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Cria uma nova zona de entrega", description = "Cria uma nova zona de entrega e seu valor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Zona de entrega criada"),
            @ApiResponse(responseCode = "400", description = "Zona de entrega já cadastrada"),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrado")
    })
    public ResponseEntity<DeliveryZoneDto> save(
            @RequestBody @Valid DeliveryZonePostDto deliveryZone,
            @CurrentUser Administrator currentUser) {
        DeliveryZoneDto createdZone = deliveryZoneService.save(deliveryZone, currentUser.getId());
        return ResponseEntity.ok(createdZone);
    }

    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Atualiza uma zona de entrega", description = "Atualiza uma zona de entrega e seu valor")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Zona de entrega atualizada"),
            @ApiResponse(responseCode = "400", description = "Zona de entrega já cadastrada"),
            @ApiResponse(responseCode = "404", description = "Zona de entrega não encontrada")
    })
    public ResponseEntity<DeliveryZoneDto> update(@PathVariable Long id, @Valid @RequestBody DeliveryZonePostDto deliveryZone) {
        return ResponseEntity.ok(deliveryZoneService.update(id, deliveryZone));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @Operation(summary = "Deleta uma zona de entrega", description = "Deleta uma zona de entrega")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Zona de entrega deletada"),
            @ApiResponse(responseCode = "404", description = "Zona de entrega não encontrada")
    })
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deliveryZoneService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

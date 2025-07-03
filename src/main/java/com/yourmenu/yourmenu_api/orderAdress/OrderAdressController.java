package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressPostDto;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/orderAdress")
@RequiredArgsConstructor
public class OrderAdressController {

    private final OrderAdressService orderAdressService;

    @PostMapping()
    public ResponseEntity<OrderAdressDto> createOrderAdress(
            @RequestBody @Valid OrderAdressPostDto dto,
            @CurrentUser Administrator currentUser
    ) {
        OrderAdressDto createdOrderAdress = orderAdressService.save(dto, currentUser.getId());

        URI location = URI.create("/orderAdress/" + createdOrderAdress.id().toString());
        return ResponseEntity
                .created(location)
                .body(createdOrderAdress);
    }
}

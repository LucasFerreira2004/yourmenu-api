package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.deliveryZone.exception.DeliveryZoneNotFoundException;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressPostDto;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAdressService {

    private final OrderAdressRepository orderAdressRepository;
    private final OrderAdressMapper orderAdressMapper;

    public OrderAdressDto save(OrderAdressPostDto dto, String administratorId) {
        OrderAdress entity = orderAdressMapper.toEntity(dto);

        // verificar se existe um orderAdress já cadastrado para o order, se existir, retorna o orderAdress
        if(orderAdressRepository.existsByOrderId(dto.orderId())) {
            return orderAdressMapper.toDto(
                    orderAdressRepository
                            .findByOrderId(dto.orderId())
                            .orElseThrow(() -> new ResourceNotFoundException("id", "Id de order para orderAdress não encontrado")));
        }

        return orderAdressMapper.toDto(orderAdressRepository.save(entity));
    }

    public OrderAdressDto getByOrderId(Long orderId) {
        return orderAdressMapper.toDto(
                                orderAdressRepository.findByOrderId(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("id", "Id de order para orderAdress não encontrado")));
    }
}

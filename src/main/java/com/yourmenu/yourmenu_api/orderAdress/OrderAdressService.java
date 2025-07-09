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

    public OrderAdress save(OrderAdressPostDto dto, Long orderId) {
        OrderAdress entity = orderAdressMapper.toEntity(dto, orderId);
        // verificar se existe um orderAdress já cadastrado para o order, se existir, retorna o orderAdress
        if (orderAdressRepository.existsByOrderId(orderId)) {
            return orderAdressRepository
                    .findByOrderId(orderId)
                    .orElseThrow(() -> new ResourceNotFoundException("id", "Id de order para orderAdress não encontrado"));
        }
        return orderAdressRepository.save(entity);

    }

    public OrderAdressDto getByOrderId(Long orderId) {
        return orderAdressMapper.toDTO(
                                orderAdressRepository.findByOrderId(orderId)
                                .orElseThrow(() -> new ResourceNotFoundException("id", "Id de order para orderAdress não encontrado")));
    }
}

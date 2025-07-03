package com.yourmenu.yourmenu_api.orderAdress;

import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressDto;
import com.yourmenu.yourmenu_api.orderAdress.dto.OrderAdressPostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderAdressService {

    private final OrderAdressRepository orderAdressRepository;
    private final OrderAdressMapper orderAdressMapper;

    public OrderAdressDto save(OrderAdressPostDto dto, String administratorId) {
        OrderAdress entity = orderAdressMapper.toEntity(dto);
        return orderAdressMapper.toDto(orderAdressRepository.save(entity));
    }
}

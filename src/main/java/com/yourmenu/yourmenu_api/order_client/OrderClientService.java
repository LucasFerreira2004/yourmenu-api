package com.yourmenu.yourmenu_api.order_client;

import com.yourmenu.yourmenu_api.order.Order;
import com.yourmenu.yourmenu_api.order.OrderRepository;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientFullDTO;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientSaveDTO;
import com.yourmenu.yourmenu_api.shared.globalExceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderClientService {

    @Autowired
    private OrderClientRepository orderClientRepository;

    @Autowired
    private OrderClientMapper orderClientMapper;

    @Autowired
    private OrderRepository orderRepository;

    public OrderClient save(OrderClientSaveDTO dto, Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Id do pedido"));

        OrderClient orderClient = orderClientMapper.toEntity(dto, order);
        return orderClientRepository.save(orderClient);
    }

    public OrderClientFullDTO findById(Long orderClientId) {
        OrderClient orderClient = orderClientRepository.findById(orderClientId)
                .orElseThrow(() -> new ResourceNotFoundException("Id do client"));

        return orderClientMapper.toFullDTO(orderClient);
    }

}

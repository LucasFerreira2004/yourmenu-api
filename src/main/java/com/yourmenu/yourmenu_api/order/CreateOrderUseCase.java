package com.yourmenu.yourmenu_api.order;

import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.order.mappers.OrderMapper;
import com.yourmenu.yourmenu_api.orderAdress.OrderAdress;
import com.yourmenu.yourmenu_api.orderAdress.OrderAdressService;
import com.yourmenu.yourmenu_api.orderItem.OrderItem;
import com.yourmenu.yourmenu_api.orderItem.OrderItemService;
import com.yourmenu.yourmenu_api.order_client.OrderClient;
import com.yourmenu.yourmenu_api.order_client.OrderClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreateOrderUseCase {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAdressService orderAdressService;

    @Autowired
    private OrderClientService orderClientService;

    @Transactional
    public OrderDTO execute(OrderSaveDTO saveDTO, String restaurantId) {
        try {
            Order order =  orderService.saveOrder(saveDTO, restaurantId);
            List<OrderItem> items =  orderItemService.saveOrderItems(saveDTO.orderItems(), order.getId());
            order.setOrderItems(items);
            OrderAdress orderAdress = orderAdressService.save(saveDTO.orderAdress(), order.getId());
            order.setOrderAdress(orderAdress);
            OrderClient orderClient = orderClientService.save(saveDTO.orderClient(), order.getId());
            order.setOrderClient(orderClient);

            return orderMapper.toDTO(order);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

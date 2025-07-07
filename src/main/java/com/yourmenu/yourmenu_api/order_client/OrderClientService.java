package com.yourmenu.yourmenu_api.order_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderClientService {

    @Autowired
    private OrderClientRepository orderClientRepository;

    @Autowired
    private OrderClientMapper orderClientMapper;

    public void saveOrderClinet(OrderClient orderClient) {

    }

}

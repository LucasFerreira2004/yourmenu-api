package com.yourmenu.yourmenu_api.order;

import com.yourmenu.yourmenu_api.administrator.Administrator;
import com.yourmenu.yourmenu_api.order.dto.OrderByStatusDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderDTO;
import com.yourmenu.yourmenu_api.order.dto.OrderSaveDTO;
import com.yourmenu.yourmenu_api.order.dto.OrdersSumaryDTO;
import com.yourmenu.yourmenu_api.order_client.OrderClientService;
import com.yourmenu.yourmenu_api.order_client.dto.OrderClientFullDTO;
import com.yourmenu.yourmenu_api.shared.notations.currentUser.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/restaurant/{restaurantId}/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderClientService orderClientService;

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllByRestaurant(@PathVariable String restaurantId) {
        List<OrderDTO> orders = orderService.getAllByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<OrderDTO>> getAllByRestaurantAndDate(@PathVariable String restaurantId, @RequestParam LocalDate date) {
        List<OrderDTO> orders = orderService.getAllByRestaurantAndDate(restaurantId, date);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getById(@PathVariable String restaurantId, @PathVariable Long orderId) {
        OrderDTO order = orderService.getById(restaurantId, orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/client/{orderClientId}")
    public ResponseEntity<OrderClientFullDTO> getClientById(@PathVariable Long orderClientId) {
        OrderClientFullDTO fullDTO = orderClientService.findById(orderClientId);
        return ResponseEntity.ok(fullDTO);
    }

    @GetMapping("/status/by-date")
    public ResponseEntity<List<OrderByStatusDTO>> getAllByRestaurantDateAndStatus(@PathVariable String restaurantId, @RequestParam LocalDate date) {
        List<OrderByStatusDTO> orders = orderService.getAlByRestaurantDateAndStatus(restaurantId, date);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateStatus(
            @PathVariable(value = "orderId") Long orderId,
            @RequestParam(value = "restaurantId") String restaurantId,
            @RequestParam OrderStatus status) {
        OrderDTO order = orderService.updateStatus(restaurantId, orderId, status);
        return ResponseEntity.ok(order);
    }

    //esse get não pode ser público, só pode ser feito por adm
    @GetMapping("/summary/by-date")
    public ResponseEntity<OrdersSumaryDTO> getSummaryByDate(@PathVariable String restaurantId, @RequestParam LocalDate date, @CurrentUser Administrator currentUser) {
        OrdersSumaryDTO summary = orderService.getSummaryByDate(restaurantId, date, currentUser.getId());
        return ResponseEntity.ok(summary);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> saveOder(@PathVariable String restaurantId, @RequestBody OrderSaveDTO saveDTO) {
        OrderDTO order = createOrderUseCase.execute(saveDTO, restaurantId);
        return ResponseEntity.ok(order);
    }
}

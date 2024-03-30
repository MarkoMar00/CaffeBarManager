package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.OrderDto;
import hr.tvz.pios.caffebarmanager.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @GetMapping("all")
    public List<OrderDto> findAllOrders() {
        return orderService.findAllOrders();
    }

    @GetMapping("/")
    public ResponseEntity<OrderDto> getOrderById(@RequestParam Long id) {
        return orderService.findById(id)
                .map(
                        order -> ResponseEntity.status(HttpStatus.OK).body(order)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PutMapping("/update/{orderId}/{orderStatus}")
    public OrderDto update(@PathVariable(value = "orderId") Long orderId,
                           @PathVariable(value = "orderStatus") Integer orderStatus) {

        return orderService.updateOrderStatus(orderId, orderStatus);

    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto){
        return orderService.save(orderDto)
                .map(
                        order -> ResponseEntity.status(HttpStatus.CREATED).body(order)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
}

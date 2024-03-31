package hr.tvz.pios.caffebarmanager.service;


import hr.tvz.pios.caffebarmanager.dto.OrderDto;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<OrderDto> findAllOrders();

    Optional<OrderDto> findById(Long id);

    OrderDto updateOrderStatus(Long orderId, Integer orderStatus);

    Optional<OrderDto> save(OrderDto orderDto);
}

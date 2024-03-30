package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.dto.OrderDto;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    OrderRepository orderRepository;

    @Override
    public List<OrderDto> findAllOrders() {
        return orderRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDto> findById(Long id) {
        return orderRepository.findById(id).map(this::mapToDto);
    }

    @Override
    public OrderDto updateOrderStatus(Long recipeId, Integer orderStatus) {

        Order order = orderRepository.findById(recipeId).orElseThrow();

        order.setOrderStatus(orderStatus);

        Order updatedOrderStatus = orderRepository.save(order);

        return mapToDto(updatedOrderStatus);
    }

    @Override
    public Optional<OrderDto> save(OrderDto orderDto) {
        return Optional.of(mapToDto(orderRepository.save(mapToOrder(orderDto))));
    }

    private Order mapToOrder(OrderDto orderDto) {
        Order order =  new Order();

        order.setId(orderDto.getId());
        order.setIssueTimeOfOrder(orderDto.getIssueTimeOfOrder());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setTableNumber(orderDto.getTableNumber());

        return order;
    }

    private OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();

        orderDto.setId(order.getId());
        orderDto.setIssueTimeOfOrder(order.getIssueTimeOfOrder());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setTableNumber(order.getTableNumber());

        return orderDto;
    }
}

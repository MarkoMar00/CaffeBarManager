package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import hr.tvz.pios.caffebarmanager.domain.User;
import hr.tvz.pios.caffebarmanager.dto.OrderDto;
import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.enums.OrderStatusEnum;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.repository.UserRepository;
import hr.tvz.pios.caffebarmanager.service.OrderService;
import hr.tvz.pios.caffebarmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {


    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;
    AutoCloseable autoCloseable;
    Order order;
    Order order2;
    OrderDto orderDto;
    OrderDto order2Dto;
    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository);

        // Create sample order
        order = new Order();
        order.setId(3L);
        order.setTableNumber(12);
        order.setOrderStatus(0);
        order.setIssueTimeOfOrder(LocalDateTime.now());
        order.setOrderStatistic(new OrderStatistic());
        order.setOrderArticles(new ArrayList<>());

        order2=new Order();
        order2.setId(2L);
        order2.setTableNumber(11);
        order2.setOrderStatus(1);
        order2.setIssueTimeOfOrder(LocalDateTime.now());
        order2.setOrderStatistic(new OrderStatistic());
        order2.setOrderArticles(new ArrayList<>());

        orderDto = new OrderDto(order.getId(), order.getIssueTimeOfOrder(), order.getOrderStatus(), order.getTableNumber());
        order2Dto = new OrderDto(order2.getId(), order2.getIssueTimeOfOrder(), order2.getOrderStatus(), order2.getTableNumber());

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findAllOrders_ReturnsListOfOrderDtos() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> result = orderService.findAllOrders();

        assertEquals(2, result.size());
        assertEquals(order.getId(), result.get(0).getId());
        assertEquals(order.getTableNumber(), result.get(0).getTableNumber());

    }
    @Test
    void findById_ReturnsOrderDto() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));

        Optional<OrderDto> result = orderService.findById(order.getId());

        assertTrue(result.isPresent());
        assertEquals(order.getId(), result.get().getId());
    }

    @Test
    void updateOrderStatus_UpdatesOrderStatus() {
        when(orderRepository.findById(order.getId())).thenReturn(Optional.of(order));
        Order updatedOrder = order;
        updatedOrder.setOrderStatus(1);
        when(orderRepository.save(order)).thenReturn(updatedOrder);

        orderService.updateOrderStatus(order.getId(), 1);

        verify(orderRepository, times(1)).findById(order.getId());
        verify(orderRepository, times(1)).save(order);
        assertEquals(1, order.getOrderStatus());
    }

    @Test
    void save_ValidOrderDto_ReturnsSavedOrderDto() {

        when(orderRepository.save(any(Order.class))).thenReturn(order);


        Optional<OrderDto> result = orderService.save(orderDto);


        assertTrue(result.isPresent());
        assertEquals(order.getId(), result.get().getId());
        assertEquals(order.getOrderStatus(), result.get().getOrderStatus());
        assertEquals(order.getTableNumber(), result.get().getTableNumber());
        assertEquals(order.getIssueTimeOfOrder(), result.get().getIssueTimeOfOrder());

    }
    

}
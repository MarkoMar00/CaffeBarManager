package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.OrderDto;
import hr.tvz.pios.caffebarmanager.service.OrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private List<OrderDto> orderList;
    private OrderDto orderDto;
    private ResponseEntity<List<OrderDto>> orderListResponseEntity;
    private ResponseEntity<OrderDto> orderResponseEntityCreated;
    private ResponseEntity<OrderDto> orderResponseEntityOk;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        orderList = new ArrayList<>();
        orderDto = new OrderDto(1L, LocalDateTime.now(), 1, 1);
        orderList.add(orderDto);

        orderListResponseEntity = new ResponseEntity<>(orderList, HttpStatus.OK);
        orderResponseEntityCreated = new ResponseEntity<>(orderDto, HttpStatus.CREATED);
        orderResponseEntityOk = new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void findAllOrdersTest() {
        when(orderService.findAllOrders()).thenReturn(orderList);

        List<OrderDto> response = orderController.findAllOrders();

        assertEquals(orderList, response);
        verify(orderService, times(1)).findAllOrders();
    }

    @Test
    public void getOrderByIdTest() {
        when(orderService.findById(1L)).thenReturn(Optional.of(orderDto));

        ResponseEntity<OrderDto> response = orderController.getOrderById(1L);

        assertEquals(orderResponseEntityOk, response);
        verify(orderService, times(1)).findById(1L);
    }

    @Test
    void updateTest() {
        when(orderService.updateOrderStatus(1L, 2)).thenReturn(orderDto);

        OrderDto response = orderController.update(1L, 2);

        assertEquals(orderDto, response);
        verify(orderService, times(1)).updateOrderStatus(1L, 2);
    }

    @Test
    public void createOrderTest() {
        when(orderService.save(orderDto)).thenReturn(Optional.of(orderDto));

        ResponseEntity<OrderDto> response = orderController.createOrder(orderDto);

        assertEquals(orderResponseEntityCreated, response);
        verify(orderService, times(1)).save(orderDto);
    }
}
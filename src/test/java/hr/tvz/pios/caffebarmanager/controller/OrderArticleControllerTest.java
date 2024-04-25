package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.service.OrderArticleService;
import hr.tvz.pios.caffebarmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderArticleControllerTest {

    @Mock
    private OrderArticleService orderArticleService;

    @InjectMocks
    private OrderArticleController orderArticleController;

    private List<OrderArticleDto> orderArticleList;
    private OrderArticleDto orderArticleDto;
    private ResponseEntity<List<OrderArticleDto>> orderArticleListResponseEntity;
    private ResponseEntity<OrderArticleDto> orderArticleResponseEntityCreated;
    private ResponseEntity<OrderArticleDto> orderArticleResponseEntityOk;
    private Article article;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        article=new Article();
        article.setId(1L); article.setName("nesica"); article.setPrice(BigDecimal.valueOf(12));article.setAvailableAmount(12); article.setDescription("obicna nes");

        orderArticleList = new ArrayList<>();

        orderArticleDto = new OrderArticleDto();
        orderArticleDto.setArticleId(1L);
        orderArticleDto.setAmount(3);
        orderArticleDto.setPricePerUnit(BigDecimal.valueOf(13));
        orderArticleDto.setArticleId(article.getId());
        orderArticleList.add(orderArticleDto);

        orderArticleListResponseEntity = new ResponseEntity<>(orderArticleList, HttpStatus.CREATED);
        orderArticleResponseEntityOk = new ResponseEntity<>(orderArticleDto, HttpStatus.OK);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testFindAllOrderArticles() {
        when(orderArticleService.getAllArticles()).thenReturn(orderArticleList);

        List<OrderArticleDto> response = orderArticleController.findAllOrderArticles();

        assertEquals(orderArticleList, response);
        verify(orderArticleService, times(1)).getAllArticles();
    }
    @Test
    public void testFindArticleById() {
        when(orderArticleService.findById(1L)).thenReturn(Optional.of(orderArticleDto));

        ResponseEntity<OrderArticleDto> response = orderArticleController.findArticleById(1L);

        assertEquals(orderArticleResponseEntityOk, response);
        verify(orderArticleService, times(1)).findById(1L);
    }

    @Test
    public void testSaveAllArticles() {
        when(orderArticleService.saveAllArticles(1L, orderArticleList)).thenReturn(Optional.of(orderArticleList));

        ResponseEntity<List<OrderArticleDto>> response = orderArticleController.saveAllArticles(1L, orderArticleList);

        assertEquals(orderArticleListResponseEntity, response);
        verify(orderArticleService, times(1)).saveAllArticles(1L, orderArticleList);
    }
}
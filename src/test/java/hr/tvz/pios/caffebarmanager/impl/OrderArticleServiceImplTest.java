package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import hr.tvz.pios.caffebarmanager.dto.ArticleDto;
import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.repository.ArticleRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderArticleRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderStatisticsRepository;
import hr.tvz.pios.caffebarmanager.service.OrderArticleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrderArticleServiceImplTest {
    @Mock
    private OrderArticleRepository orderArticleRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ArticleRepository articleRepository;
    @Mock
    private OrderStatisticsRepository orderStatisticsRepository;

    AutoCloseable autoCloseable;

    private OrderArticleService orderArticleService;
    private OrderArticle orderArticle;

    private Article article;

    private OrderArticleDto orderArticleDto;
    private Order order;

    private OrderStatistic orderStatistic;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        orderArticleService = new OrderArticleServiceImpl(orderArticleRepository, orderRepository,articleRepository, orderStatisticsRepository);

        order=new Order();
        order.setId(1L);
        order.setIssueTimeOfOrder(LocalDateTime.now());
        order.setOrderStatus(1);
        order.setTableNumber(10);


        article=new Article();
        article.setId(3L);
        article.setName("Kava");
        article.setDescription("Obična normalna kava");
        article.setPrice(BigDecimal.valueOf(22));
        article.setAvailableAmount(99);

        orderArticle =new OrderArticle();

        orderArticle.setId(1L);
        orderArticle.setAmount(3);
        orderArticle.setPricePerUnit(BigDecimal.valueOf(12));
        orderArticle.setArticle(article);
        orderArticle.setOrder(order);

        order.setOrderArticles(List.of(orderArticle));

        orderArticleDto = new OrderArticleDto();
        orderArticleDto.setId(1L);
        orderArticleDto.setAmount(2);
        orderArticleDto.setPricePerUnit(BigDecimal.valueOf(22));
        orderArticleDto.setArticleId(article.getId());

        orderStatistic=new OrderStatistic();
        orderStatistic.setId(1L);
        orderStatistic.setTotalPrice(BigDecimal.valueOf(12));
        orderStatistic.setDateAndTime(LocalDateTime.now());
        orderStatistic.setOrder(order);

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllArticlesTest() {
        // Given
        List<OrderArticle> orderArticles = Arrays.asList(orderArticle);
        when(orderArticleRepository.findAll()).thenReturn(orderArticles);

        // When
        List<OrderArticleDto> foundOrderArticles = orderArticleService.getAllArticles();

        // Then
        assertThat(foundOrderArticles.get(0).getId()).isEqualTo(orderArticle.getId());
        assertThat(foundOrderArticles.get(0).getAmount()).isEqualTo(orderArticle.getAmount());
        assertThat(foundOrderArticles.get(0).getPricePerUnit()).isEqualTo(orderArticle.getPricePerUnit());
    }

    @Test
    void findByIdTest() {
        when(orderArticleRepository.findById(orderArticle.getId())).thenReturn(Optional.ofNullable(orderArticle));

        Optional<OrderArticleDto> foundOrderArticleDto= orderArticleService.findById(orderArticle.getId());

        assertThat(foundOrderArticleDto.get().getId()).isEqualTo(orderArticle.getId());
        assertThat(foundOrderArticleDto.get().getAmount()).isEqualTo(orderArticle.getAmount());
        assertThat(foundOrderArticleDto.get().getPricePerUnit()).isEqualTo(orderArticle.getPricePerUnit());
    }

    @Test
    void saveAllArticlesTest() {
        when(orderRepository.findById(orderArticle.getOrder().getId())).thenReturn(Optional.ofNullable(order));
        when(articleRepository.findById(orderArticle.getArticle().getId())).thenReturn(Optional.ofNullable(article));
        when(orderArticleRepository.save(any(OrderArticle.class))).thenReturn(orderArticle);
        when(orderStatisticsRepository.save(any(OrderStatistic.class))).thenReturn(orderStatistic);

        List<OrderArticleDto> orderArticleDtos=List.of(orderArticleDto);
        Optional<List<OrderArticleDto>> savedArticles= orderArticleService.saveAllArticles(order.getId(),orderArticleDtos);

        assertThat(savedArticles.get().size()).isEqualTo(orderArticleDtos.size());
        assertThat(savedArticles.get().get(0).getId()).isEqualTo(orderArticle.getId());
        assertThat(savedArticles.get().get(0).getArticleId()).isEqualTo(orderArticle.getArticle().getId());
        assertThat(savedArticles.get().get(0).getAmount()).isEqualTo(orderArticle.getAmount());
        assertThat(savedArticles.get().get(0).getPricePerUnit()).isEqualTo(orderArticle.getPricePerUnit());


    }
}
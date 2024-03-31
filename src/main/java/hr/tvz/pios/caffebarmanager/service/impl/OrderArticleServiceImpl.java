package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.Order;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.repository.ArticleRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderArticleRepository;
import hr.tvz.pios.caffebarmanager.repository.OrderRepository;
import hr.tvz.pios.caffebarmanager.service.OrderArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderArticleServiceImpl implements OrderArticleService {
    OrderArticleRepository orderArticleRepository;
    OrderRepository orderRepository;
    ArticleRepository articleRepository;

    @Override
    public List<OrderArticleDto> getAllArticles() {
        return orderArticleRepository.findAll()
                .stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<OrderArticleDto> findById(Long id) {
        return orderArticleRepository.findById(id).map(this::mapToDto);
    }

    @Override
    public Optional<List<OrderArticleDto>> saveAllArticles(Long orderId, List<OrderArticleDto> orderArticleDtos) {
        List<OrderArticleDto> savedArticles = new ArrayList<>();

        orderArticleDtos.forEach(orderArticleDto -> {
            Order order = orderRepository.findById(orderId).orElseThrow();
            Article article = articleRepository.findById(orderArticleDto.getArticleId()).orElseThrow();
            OrderArticle orderArticle = mapToOrderArticle(orderArticleDto);

            orderArticle.setOrder(order);
            orderArticle.setArticle(article);

            savedArticles.add(mapToDto(orderArticleRepository.save(orderArticle)));
        });

        return Optional.of(savedArticles);
    }

    private OrderArticleDto mapToDto(OrderArticle orderArticle) {
        OrderArticleDto orderArticleDto = new OrderArticleDto();

        orderArticleDto.setId(orderArticle.getId());
        orderArticleDto.setAmount(orderArticle.getAmount());
        orderArticleDto.setPricePerUnit(orderArticle.getPricePerUnit());
        orderArticleDto.setArticleId(orderArticle.getArticle().getId());

        return orderArticleDto;
    }

    private OrderArticle mapToOrderArticle(OrderArticleDto orderArticleDto) {
        OrderArticle orderArticle = new OrderArticle();

        orderArticle.setId(orderArticleDto.getId());
        orderArticle.setAmount(orderArticleDto.getAmount());
        orderArticle.setPricePerUnit(orderArticleDto.getPricePerUnit());

        return orderArticle;
    }
}

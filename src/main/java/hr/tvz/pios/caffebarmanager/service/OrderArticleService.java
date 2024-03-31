package hr.tvz.pios.caffebarmanager.service;

import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;

import java.util.List;
import java.util.Optional;

public interface OrderArticleService {
    List<OrderArticleDto> getAllArticles();

    Optional<OrderArticleDto> findById(Long id);

    Optional<List<OrderArticleDto>> saveAllArticles(Long orderId, List<OrderArticleDto> orderArticleDtos);
}

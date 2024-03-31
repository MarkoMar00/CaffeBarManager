package hr.tvz.pios.caffebarmanager.service;

import hr.tvz.pios.caffebarmanager.dto.ArticleDto;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    List<ArticleDto> getAllArticles();

    Optional<ArticleDto> findById(Long id);

    Optional<ArticleDto> saveArticle(ArticleDto articleDto);

    ArticleDto updateArticle(ArticleDto articleDto);
}

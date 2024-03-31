package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.dto.ArticleDto;
import hr.tvz.pios.caffebarmanager.repository.ArticleRepository;
import hr.tvz.pios.caffebarmanager.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    @Override
    public List<ArticleDto> getAllArticles() {
        return articleRepository.findAll().
                stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleDto> findById(Long id) {
        return articleRepository.findById(id).map(this::mapToDto);
    }

    @Override
    public Optional<ArticleDto> saveArticle(ArticleDto articleDto) {
        return Optional.of(mapToDto(articleRepository.save(mapToArticle(articleDto))));
    }

    @Override
    public ArticleDto updateArticle(ArticleDto articleDto) {
        Article article = articleRepository.findById(articleDto.getId()).orElseThrow();

        article.setName(articleDto.getName());
        article.setDescription(articleDto.getDescription());
        article.setPrice(articleDto.getPrice());
        article.setAvailableAmount(articleDto.getAvailableAmount());

        Article updatedArticle = articleRepository.save(article);

        return mapToDto(updatedArticle);
    }

    private ArticleDto mapToDto(Article article) {
        ArticleDto articleDto = new ArticleDto();

        articleDto.setId(article.getId());
        articleDto.setName(article.getName());
        articleDto.setDescription(article.getDescription());
        articleDto.setPrice(article.getPrice());
        articleDto.setAvailableAmount(article.getAvailableAmount());

        return articleDto;
    }

    private Article mapToArticle(ArticleDto articleDto) {
        Article article = new Article();

        article.setId(articleDto.getId());
        article.setName(articleDto.getName());
        article.setDescription(articleDto.getDescription());
        article.setPrice(articleDto.getPrice());
        article.setAvailableAmount(articleDto.getAvailableAmount());

        return article;
    }
}

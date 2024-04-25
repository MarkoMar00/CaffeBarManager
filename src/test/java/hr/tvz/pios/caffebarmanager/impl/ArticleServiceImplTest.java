package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import hr.tvz.pios.caffebarmanager.domain.User;
import hr.tvz.pios.caffebarmanager.dto.ArticleDto;
import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.repository.ArticleRepository;
import hr.tvz.pios.caffebarmanager.service.ArticleService;
import hr.tvz.pios.caffebarmanager.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ArticleServiceImplTest {
    @Mock
    private ArticleRepository articleRepository;
    private ArticleService articleService;
    AutoCloseable autoCloseable;
    Article article;
    ArticleDto articleDto;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        articleService = new ArticleServiceImpl(articleRepository);

        article = new Article();
        article.setId(3L);
        article.setName("Kava");
        article.setDescription("Obična normalna kava");
        article.setPrice(BigDecimal.valueOf(22));
        article.setAvailableAmount(99);

        when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));

        articleDto = new ArticleDto(article.getId(), article.getName(), article.getDescription(), article.getPrice(), article.getAvailableAmount());
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getAllArticles() {
        List<Article> articles = Arrays.asList(article);
        when(articleRepository.findAll()).thenReturn(articles);

        List<ArticleDto> foundArticles = articleService.getAllArticles();


        assertThat(foundArticles.get(0).getId()).isEqualTo(article.getId());
        assertThat(foundArticles.get(0).getName()).isEqualTo(article.getName());
        assertThat(foundArticles.get(0).getDescription()).isEqualTo(article.getDescription());
        assertThat(foundArticles.get(0).getPrice()).isEqualTo(article.getPrice());
        assertThat(foundArticles.get(0).getAvailableAmount()).isEqualTo(article.getAvailableAmount());

    }

    @Test
    void findById() {
        when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));

        Optional<ArticleDto> foundArticle = articleService.findById(article.getId());

        assertThat(foundArticle).isPresent();
        assertThat(foundArticle.get().getId()).isEqualTo(article.getId());
        assertThat(foundArticle.get().getName()).isEqualTo(article.getName());
        assertThat(foundArticle.get().getDescription()).isEqualTo(article.getDescription());
        assertThat(foundArticle.get().getPrice()).isEqualTo(article.getPrice());
        assertThat(foundArticle.get().getAvailableAmount()).isEqualTo(article.getAvailableAmount());

    }

    @Test
    void saveArticle() {

        when(articleRepository.save(any())).thenReturn(article);

        ArticleDto savedArticle = articleService.saveArticle(articleDto).get();

        assertThat(savedArticle.getName()).isEqualTo("Kava");
        assertThat(savedArticle.getDescription()).isEqualTo("Obična normalna kava");
        assertThat(savedArticle.getPrice()).isEqualTo(BigDecimal.valueOf(22));
        assertThat(savedArticle.getAvailableAmount()).isEqualTo(99);
    }

    @Test
    void updateArticle() {
        when(articleRepository.findById(article.getId())).thenReturn(Optional.of(article));

        ArticleDto updatedArticleDto = new ArticleDto(article.getId(), "Updated Name", "Updated Description", BigDecimal.valueOf(30), 50);
        Article updatedArticle= article;
        updatedArticle.setName(updatedArticleDto.getName());
        updatedArticle.setDescription(updatedArticleDto.getDescription());
        updatedArticle.setPrice(updatedArticleDto.getPrice());
        updatedArticle.setAvailableAmount(updatedArticleDto.getAvailableAmount());

        when(articleRepository.save(article)).thenReturn(updatedArticle);

        ArticleDto updatedArticleNew= articleService.updateArticle(updatedArticleDto);

        assertThat(updatedArticleNew.getName()).isEqualTo("Updated Name");
        assertThat(updatedArticleNew.getDescription()).isEqualTo("Updated Description");
        assertThat(updatedArticleNew.getPrice()).isEqualTo(BigDecimal.valueOf(30));
        assertThat(updatedArticleNew.getAvailableAmount()).isEqualTo(50);
    }
}
package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.ArticleDto;
import hr.tvz.pios.caffebarmanager.service.ArticleService;
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

class ArticleControllerTest {

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;

    private List<ArticleDto> articleList;
    private ArticleDto articleDto;
    private ResponseEntity<List<ArticleDto>> articleListResponseEntity;
    private ResponseEntity<ArticleDto> articleResponseEntityCreated;
    private ResponseEntity<ArticleDto> articleResponseEntityOk;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        articleList = new ArrayList<>();
        articleDto = new ArticleDto(1L, "Test Article", "Test Description", new BigDecimal("10.00"), 10);
        articleList.add(articleDto);

        articleListResponseEntity = new ResponseEntity<>(articleList, HttpStatus.OK);
        articleResponseEntityCreated = new ResponseEntity<>(articleDto, HttpStatus.CREATED);
        articleResponseEntityOk = new ResponseEntity<>(articleDto, HttpStatus.OK);
    }
    @AfterEach
    void tearDown() {
    }
    @Test
    public void getAllArticlesTest() {
        when(articleService.getAllArticles()).thenReturn(articleList);

        List<ArticleDto> response = articleController.getAllArticles();

        assertEquals(articleList, response);
        verify(articleService, times(1)).getAllArticles();
    }






    @Test
    public void getArticleByIdTest() {
        when(articleService.findById(1L)).thenReturn(Optional.of(articleDto));

        ResponseEntity<ArticleDto> response = articleController.getArticleById(1L);

        assertEquals(articleResponseEntityOk, response);
        verify(articleService, times(1)).findById(1L);
    }

    @Test
    void saveArticle() {
        when(articleService.saveArticle(articleDto)).thenReturn(Optional.of(articleDto));

        ResponseEntity<ArticleDto> response = articleController.saveArticle(articleDto);

        assertEquals(articleResponseEntityCreated, response);
        verify(articleService, times(1)).saveArticle(articleDto);
    }

    @Test
    void updateArticle() {
        when(articleService.updateArticle(articleDto)).thenReturn(articleDto);

        ArticleDto response = articleController.updateArticle(articleDto);

        assertEquals(articleDto, response);
        verify(articleService, times(1)).updateArticle(articleDto);
    }
}
package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.ArticleDto;
import hr.tvz.pios.caffebarmanager.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ArticleController {

    ArticleService articleService;

    @GetMapping("/all")
    public List<ArticleDto> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/")
    public ResponseEntity<ArticleDto> getArticleById(@RequestParam Long id) {
        return articleService.findById(id)
                .map(
                        article -> ResponseEntity.status(HttpStatus.OK).body(article)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PostMapping("/create")
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody ArticleDto articleDto) {
        return articleService.saveArticle(articleDto)
                .map(
                        article -> ResponseEntity.status(HttpStatus.CREATED).body(article)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }

    @PutMapping("/update")
    public ArticleDto updateArticle(@RequestBody ArticleDto articleDto) {
        return articleService.updateArticle(articleDto);
    }
}

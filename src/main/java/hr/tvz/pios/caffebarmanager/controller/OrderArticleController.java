package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.OrderArticleDto;
import hr.tvz.pios.caffebarmanager.service.OrderArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderArticle")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class OrderArticleController {
    OrderArticleService orderArticleService;

    @GetMapping("all")
    public List<OrderArticleDto> findAllOrderArticles() {
        return orderArticleService.getAllArticles();
    }

    @GetMapping("/")
    public ResponseEntity<OrderArticleDto> findArticleById(Long id) {
        return orderArticleService.findById(id)
                .map(
                        article -> ResponseEntity.status(HttpStatus.OK).body(article)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                );
    }

    @PostMapping("/saveArticles/{orderId}/")
    public ResponseEntity<List<OrderArticleDto>> saveAllArticles(@PathVariable(value = "orderId") Long orderId,
                                                                 @RequestBody List<OrderArticleDto> orderArticleDtos) {

        return orderArticleService.saveAllArticles(orderId, orderArticleDtos)
                .map(
                        articles -> ResponseEntity.status(HttpStatus.CREATED).body(articles)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
}

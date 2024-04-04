package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.dto.NewsletterDto;
import hr.tvz.pios.caffebarmanager.service.NewsletterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mailinList")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class NewsLetterController {

    NewsletterService newsletterService;

    @GetMapping("all")
    public List<NewsletterDto> getAllSubscribers() {
        return newsletterService.getAllNewsletterSubscribers();
    }

    @PostMapping("/save")
    public ResponseEntity<NewsletterDto> saveSubscriber(@RequestBody NewsletterDto newsletterDto) {
        return newsletterService.saveNewsLetterSubscriber(newsletterDto)
                .map(
                        subscriber -> ResponseEntity.status(HttpStatus.CREATED).body(newsletterDto)
                ).orElseGet(
                        () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
                );
    }
}

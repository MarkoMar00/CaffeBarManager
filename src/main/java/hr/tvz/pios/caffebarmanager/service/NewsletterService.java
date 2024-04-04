package hr.tvz.pios.caffebarmanager.service;

import hr.tvz.pios.caffebarmanager.dto.NewsletterDto;

import java.util.List;
import java.util.Optional;

public interface NewsletterService {
    List<NewsletterDto> getAllNewsletterSubscribers();

    Optional<NewsletterDto> saveNewsLetterSubscriber(NewsletterDto newsletterDto);
}

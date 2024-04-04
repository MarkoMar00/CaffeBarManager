package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.NewsletterSubscriber;
import hr.tvz.pios.caffebarmanager.dto.NewsletterDto;
import hr.tvz.pios.caffebarmanager.repository.NewsletterRepository;
import hr.tvz.pios.caffebarmanager.service.NewsletterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NewsletterServiceImpl implements NewsletterService {

    NewsletterRepository newsletterRepository;

    @Override
    public List<NewsletterDto> getAllNewsletterSubscribers() {
        return newsletterRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<NewsletterDto> saveNewsLetterSubscriber(NewsletterDto newsletterDto) {
        return Optional.of(mapToDto(newsletterRepository.save(mapToNewsLetterSubscriber(newsletterDto))));
    }

    private NewsletterDto mapToDto(NewsletterSubscriber newsletterSubscriber) {
        NewsletterDto newsletterDto = new NewsletterDto();

        newsletterDto.setId(newsletterSubscriber.getId());
        newsletterDto.setEmail(newsletterSubscriber.getEmail());

        return newsletterDto;
    }

    private NewsletterSubscriber mapToNewsLetterSubscriber(NewsletterDto newsletterDto) {
        NewsletterSubscriber newsletterSubscriber = new NewsletterSubscriber();

        newsletterSubscriber.setId(newsletterDto.getId());
        newsletterSubscriber.setEmail(newsletterDto.getEmail());

        return newsletterSubscriber;
    }
}

package hr.tvz.pios.caffebarmanager.service.impl;

import hr.tvz.pios.caffebarmanager.domain.NewsletterSubscriber;
import hr.tvz.pios.caffebarmanager.dto.NewsletterDto;
import hr.tvz.pios.caffebarmanager.repository.NewsletterRepository;
import hr.tvz.pios.caffebarmanager.service.NewsletterService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class NewsletterServiceImplTest {

    @Mock
    private NewsletterRepository newsletterRepository;
    private NewsletterService newsletterService;

    AutoCloseable autoCloseable;

    NewsletterDto newsletterDto;
    NewsletterSubscriber newsletterSubscriber;

    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);

        newsletterDto=new NewsletterDto(1L, "novisub@gmail.com");
        newsletterSubscriber=mapToNewsLetterSubscriber(newsletterDto);


        newsletterService= new NewsletterServiceImpl(newsletterRepository);

    }



    @Test
    public void getAllNewsletterSubscribers() {
        NewsletterDto newsletterDto2=new NewsletterDto(2L,"novisub2@gmail.com");
        NewsletterDto newsletterDto3=new NewsletterDto(3L,"novisub3@gmail.com");
        NewsletterDto newsletterDto4=new NewsletterDto(4L,"novisub4@gmail.com");
        NewsletterDto newsletterDto5=new NewsletterDto(5L,"novisub5@gmail.com");
        NewsletterSubscriber newsletterSubscriber2= mapToNewsLetterSubscriber(newsletterDto2);
        NewsletterSubscriber newsletterSubscriber3= mapToNewsLetterSubscriber(newsletterDto3);
        NewsletterSubscriber newsletterSubscriber4= mapToNewsLetterSubscriber(newsletterDto4);
        NewsletterSubscriber newsletterSubscriber5= mapToNewsLetterSubscriber(newsletterDto5);
        ArrayList subscribers= new ArrayList<>();
        subscribers.add(newsletterSubscriber);
        subscribers.add(newsletterSubscriber2);
        subscribers.add(newsletterSubscriber3);
        subscribers.add(newsletterSubscriber4);
        subscribers.add(newsletterSubscriber5);

        when(newsletterRepository.findAll()).thenReturn(subscribers);

        List<NewsletterDto> result= newsletterService.getAllNewsletterSubscribers();

        assertThat(result.get(0).getEmail()).isEqualTo(newsletterDto.getEmail());
        assertThat(result.get(1).getEmail()).isEqualTo(newsletterDto2.getEmail());
        assertThat(result.get(2).getEmail()).isEqualTo(newsletterDto3.getEmail());
        assertThat(result.get(3).getEmail()).isEqualTo(newsletterDto4.getEmail());
        assertThat(result.get(4).getEmail()).isEqualTo(newsletterDto5.getEmail());

    }

    @Test
    void saveNewsLetterSubscriber() {
        when(newsletterRepository.save(any(NewsletterSubscriber.class))).thenReturn(newsletterSubscriber);

        Optional<NewsletterDto> result= newsletterService.saveNewsLetterSubscriber(newsletterDto);

        assertThat(result.get().getEmail()).isEqualTo(newsletterDto.getEmail());
        assertThat(result.get().getId()).isEqualTo(newsletterDto.getId());

    }

    private NewsletterSubscriber mapToNewsLetterSubscriber(NewsletterDto newsletterDto) {
        NewsletterSubscriber newsletterSubscriber = new NewsletterSubscriber();

        newsletterSubscriber.setId(newsletterDto.getId());
        newsletterSubscriber.setEmail(newsletterDto.getEmail());

        return newsletterSubscriber;
    }

}
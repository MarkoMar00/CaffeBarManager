package hr.tvz.pios.caffebarmanager.controller;

import hr.tvz.pios.caffebarmanager.domain.NewsletterSubscriber;
import hr.tvz.pios.caffebarmanager.dto.NewsletterDto;
import hr.tvz.pios.caffebarmanager.dto.UserDto;
import hr.tvz.pios.caffebarmanager.service.NewsletterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class NewsLetterControllerTest {
    @Mock
    private NewsletterService newsletterService;
    @InjectMocks
    private NewsLetterController newsLetterController;

    private List<NewsletterDto> subscribers;

    private NewsletterDto newsletterDto;

    private NewsletterSubscriber newsletterSubscriber;

    private ResponseEntity<NewsletterDto> newsletterResponseEntityCreated;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        subscribers=new ArrayList<>();
        newsletterDto=new NewsletterDto(1L,"novimail@mail.com");
        newsletterSubscriber= mapToNewsLetterSubscriber(newsletterDto);
        newsletterResponseEntityCreated = new ResponseEntity<>(newsletterDto, HttpStatus.CREATED);

    }

    @Test
    void getAllSubscribers() {
        NewsletterDto newsletterDto2=new NewsletterDto(2L,"novisub2@gmail.com");
        NewsletterDto newsletterDto3=new NewsletterDto(3L,"novisub3@gmail.com");
        NewsletterDto newsletterDto4=new NewsletterDto(4L,"novisub4@gmail.com");
        NewsletterDto newsletterDto5=new NewsletterDto(5L,"novisub5@gmail.com");
        NewsletterSubscriber newsletterSubscriber2= mapToNewsLetterSubscriber(newsletterDto2);
        NewsletterSubscriber newsletterSubscriber3= mapToNewsLetterSubscriber(newsletterDto3);
        NewsletterSubscriber newsletterSubscriber4= mapToNewsLetterSubscriber(newsletterDto4);
        NewsletterSubscriber newsletterSubscriber5= mapToNewsLetterSubscriber(newsletterDto5);
        ArrayList subscribers= new ArrayList<>();
        subscribers.add(newsletterDto);
        subscribers.add(newsletterDto2);
        subscribers.add(newsletterDto3);
        subscribers.add(newsletterDto4);
        subscribers.add(newsletterDto5);

        when(newsletterService.getAllNewsletterSubscribers()).thenReturn(subscribers);
        List<NewsletterDto> response= newsLetterController.getAllSubscribers();

        assertEquals(response.get(0).getEmail(), newsletterDto.getEmail());
        assertEquals(response.get(1).getEmail(), newsletterDto2.getEmail());
        assertEquals(response.get(2).getEmail(), newsletterDto3.getEmail());
        assertEquals(response.get(3).getEmail(), newsletterDto4.getEmail());
        assertEquals(response.get(4).getEmail(), newsletterDto5.getEmail());

    }

    @Test
    void saveSubscriber() {
        when(newsletterService.saveNewsLetterSubscriber(newsletterDto)).thenReturn(Optional.ofNullable(newsletterDto));
        ResponseEntity<NewsletterDto> response= newsLetterController.saveSubscriber(newsletterDto);

        assertEquals(newsletterResponseEntityCreated, response);
    }

    private NewsletterSubscriber mapToNewsLetterSubscriber(NewsletterDto newsletterDto) {
        NewsletterSubscriber newsletterSubscriber = new NewsletterSubscriber();

        newsletterSubscriber.setId(newsletterDto.getId());
        newsletterSubscriber.setEmail(newsletterDto.getEmail());

        return newsletterSubscriber;
    }
}
package hr.tvz.pios.caffebarmanager.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "NEWSLETTER_SUBSCRIBERS")
public class NewsletterSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
}

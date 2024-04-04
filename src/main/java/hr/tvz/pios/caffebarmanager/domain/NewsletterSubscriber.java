package hr.tvz.pios.caffebarmanager.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "NEWSLETTER_SUBSCRIBERS")
public class NewsletterSubscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

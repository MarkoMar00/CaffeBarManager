package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.NewsletterSubscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsletterRepository extends JpaRepository<NewsletterSubscriber, Long> {
}

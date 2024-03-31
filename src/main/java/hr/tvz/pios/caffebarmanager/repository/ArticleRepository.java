package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}

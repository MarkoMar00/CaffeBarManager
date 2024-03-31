package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.OrderArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderArticleRepository extends JpaRepository<OrderArticle, Long> {

}

package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.OrderStatistic;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderStatisticsRepository extends JpaRepository<OrderStatistic, Long> {
}

package hr.tvz.pios.caffebarmanager.repository;

import hr.tvz.pios.caffebarmanager.domain.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
    Optional<SpecialOffer> findByName(String name);

}

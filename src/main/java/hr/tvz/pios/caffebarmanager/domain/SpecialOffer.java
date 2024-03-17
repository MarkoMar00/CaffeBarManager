package hr.tvz.pios.caffebarmanager.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SPECIAL_OFFERS")
public class SpecialOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDate validFrom;

    private LocalDate validTo;
}

package hr.tvz.pios.caffebarmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecialOfferDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDate validFrom;

    private LocalDate validTo;
}

package hr.tvz.pios.caffebarmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer availableAmount;
}

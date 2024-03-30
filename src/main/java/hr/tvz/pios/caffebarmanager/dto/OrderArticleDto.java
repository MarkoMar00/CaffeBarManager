package hr.tvz.pios.caffebarmanager.dto;

import hr.tvz.pios.caffebarmanager.domain.Article;
import hr.tvz.pios.caffebarmanager.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderArticleDto {
    private Long id;

    private Integer amount;

    private BigDecimal pricePerUnit;

    private Order order;

    private Article article;
}

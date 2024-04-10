package hr.tvz.pios.caffebarmanager.dto;


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

    private Long articleId;

    private Long orderId;
}

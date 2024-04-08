package hr.tvz.pios.caffebarmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatisticDto {
    private Long id;

    private BigDecimal totalPrice;

    private LocalDateTime dateAndTime;

}

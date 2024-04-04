package hr.tvz.pios.caffebarmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsletterDto {
    private Long id;
    private String email;
}

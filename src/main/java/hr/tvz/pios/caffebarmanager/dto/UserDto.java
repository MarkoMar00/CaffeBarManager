package hr.tvz.pios.caffebarmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private Integer role;
}

package cl.ey.demo.userservice.dto.phone;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Phone {
    @NotBlank(message = "El number es obligatorio")
    private String number;
    @NotBlank(message = "El citycode es obligatorio")
    private String citycode;
    @NotBlank(message = "El contrycode es obligatorio")
    private String contrycode;
}

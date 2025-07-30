package cl.ey.demo.userservice.dto.password;

import cl.ey.demo.userservice.validator.ValidPassword;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PasswordUpdateDto {
    @ValidPassword
    @NotBlank(message = "La contraseña actual es obligatorio")
    private String oldPassword;
    @ValidPassword
    @NotBlank(message = "La nueva contraseña es obligatorio")
    private String newPassword;
}

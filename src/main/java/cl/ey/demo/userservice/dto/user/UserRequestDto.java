package cl.ey.demo.userservice.dto.user;

import java.util.List;

import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.validator.ValidEmail;
import cl.ey.demo.userservice.validator.ValidPassword;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@Builder
@RequiredArgsConstructor
@ToString
public class UserRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String name;
    @ValidEmail
    @NotBlank(message = "El email no puede estar vacío")
    private String email;
    @ValidPassword
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
    @NotNull
    @Valid
    private List<Phone> phones;
}

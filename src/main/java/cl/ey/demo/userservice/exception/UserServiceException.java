package cl.ey.demo.userservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserServiceException {

    private String mensaje;

    public UserServiceException(String mensaje) {
        this.mensaje = mensaje;
    }
}

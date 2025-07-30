package cl.ey.demo.userservice.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.dto.user.UserGetResponse;
import cl.ey.demo.userservice.dto.user.UserRequestDto;
import cl.ey.demo.userservice.dto.user.UserResponseDto;
import cl.ey.demo.userservice.service.IUserServiceServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-service/v1")
@RequiredArgsConstructor
public class UserServiceController {
    @Autowired
    private final IUserServiceServices userServiceServices;
    private static final Logger log = LoggerFactory.getLogger(UserServiceController.class);

    @PostMapping(value = "/user")
    public ResponseEntity<UserResponseDto> saveUser(
            @RequestBody @Valid UserRequestDto request) {
log.info("Inicia el proceso de guardado del usuario");
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceServices.saveUser(request));

    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserGetResponse> getUser(@PathVariable UUID id) {
        log.info("Inicia el proceso de obtencion del usuario ",id.toString());
        return ResponseEntity.ok(userServiceServices.getUserById(id));
    }

    @PutMapping("/users/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id, @RequestBody PasswordUpdateDto dto) {
        log.info("Inicia el proceso de actualizacion de credenciales del usuario ",id.toString());
        userServiceServices.updatePassword(id, dto);
        log.info("Finaliza el proceso de actualizacion de credenciales del usuario ",id.toString());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{id}/phones")
    public ResponseEntity<UserGetResponse> addPhones(@PathVariable UUID id, @RequestBody List<Phone> phones) {
        log.info("Inicia el proceso de agregar nuevos telefonos al usuario ",id.toString());
        return ResponseEntity.ok(userServiceServices.addPhones(id, phones));
    }

    @PatchMapping("/users/{id}/inactive")
    public ResponseEntity<Void> setInactive(@PathVariable UUID id) {
         log.info("Inicia el proceso de inhabilitar el usuario ",id.toString());
        userServiceServices.setUserInactive(id);
        log.info("Finaliza con exito el proceso de inhabilitar el usuario ",id.toString());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        log.info("Inicia el proceso de eliminar el usuario ",id.toString());
        userServiceServices.deleteUser(id);
        log.info("Finaliza con exito el proceso de eliminar el usuario ",id.toString());
        return ResponseEntity.noContent().build();
    }

}

package cl.ey.demo.userservice.service;

import java.util.List;
import java.util.UUID;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.dto.user.UserGetResponse;
import cl.ey.demo.userservice.dto.user.UserRequestDto;
import cl.ey.demo.userservice.dto.user.UserResponseDto;

public interface IUserServiceServices {
    
    UserResponseDto saveUser(UserRequestDto request);

    UserGetResponse getUserById(UUID id);

    void updatePassword(UUID id, PasswordUpdateDto dto);

    void setUserInactive(UUID id);

    UserGetResponse addPhones(UUID id, List<Phone> phones);

    void deleteUser(UUID id);
}

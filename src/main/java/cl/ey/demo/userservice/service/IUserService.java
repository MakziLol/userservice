package cl.ey.demo.userservice.service;

import java.util.List;
import java.util.UUID;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.entity.UserEntity;

public interface IUserService {
    UserEntity save (UserEntity user);
    UserEntity get(UUID id);
    void delete(UUID id);
    UserEntity updatePassword(UUID id,PasswordUpdateDto paswordDto);
    UserEntity setUserInactive(UUID id);

}

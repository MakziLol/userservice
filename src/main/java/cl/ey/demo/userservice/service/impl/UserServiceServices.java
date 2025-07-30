package cl.ey.demo.userservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.dto.user.UserGetResponse;
import cl.ey.demo.userservice.dto.user.UserRequestDto;
import cl.ey.demo.userservice.dto.user.UserResponseDto;
import cl.ey.demo.userservice.entity.PhoneEntity;
import cl.ey.demo.userservice.entity.UserEntity;
import cl.ey.demo.userservice.security.JwtUtil;
import cl.ey.demo.userservice.service.IUserService;
import cl.ey.demo.userservice.service.IUserServiceServices;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceServices implements IUserServiceServices {

    private final IUserService userService;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceServices.class);

    @Override
    public UserResponseDto saveUser(UserRequestDto request) {
        try {
            UserEntity user = userService.save(buildUserEntity(request), buildPhoneEntity(request));

            String token = jwtUtil.generateToken(user);
            return UserResponseDto.builder()
                    .token(token)
                    .modified(user.getModified())
                    .lastLogin(user.getLastLogin())
                    .created(user.getCreated())
                    .id(user.getId())
                    .inactive(user.isInactive())
                    .build();
        } catch (Exception e) {
            logger.error("Error al guardar usuario ", e);
            throw e;
        }
    }

    @Override
    public UserGetResponse getUserById(UUID id) {
        UserEntity user = userService.get(id);
        return buildUserGetRequestDto(user);
    }

    @Override
    public void updatePassword(UUID id, PasswordUpdateDto dto) {
        userService.updatePassword(id, dto);
    }

    @Override
    public void setUserInactive(UUID id) {
        userService.setUserInactive(id);
    }

    @Override
    public UserGetResponse addPhones(UUID id, List<Phone> phones) {
        UserEntity user = userService.addPhone(id, phones);
        return buildUserGetRequestDto(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userService.delete(id);
    }

    private UserGetResponse buildUserGetRequestDto(UserEntity user) {
        List<Phone> phones = user.getPhones().stream()
                .map(phoneEntity -> Phone.builder()
                        .citycode(phoneEntity.getCitycode())
                        .contrycode(phoneEntity.getContrycode())
                        .number(phoneEntity.getNumber())
                        .build())
                .toList();

        return UserGetResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .password(user.getPassword())
                .phones(phones)
                .build();
    }

    private UserEntity buildUserEntity(UserRequestDto requestDto) {
        return UserEntity.builder()
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .inactive(false)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .build();
    }

    private List<PhoneEntity> buildPhoneEntity(UserRequestDto requestDto) {
        return requestDto.getPhones().stream()
                .map(phone -> PhoneEntity.builder()
                        .citycode(phone.getCitycode())
                        .contrycode(phone.getContrycode())
                        .number(phone.getNumber())
                        .build())
                .toList();
    }

}

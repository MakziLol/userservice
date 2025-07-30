package cl.ey.demo.userservice.service.impl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import cl.ey.demo.userservice.dto.password.PasswordUpdateDto;
import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.dto.user.UserGetRequestDto;
import cl.ey.demo.userservice.dto.user.UserRequestDto;
import cl.ey.demo.userservice.dto.user.UserResponseDto;
import cl.ey.demo.userservice.entity.PhoneEntity;
import cl.ey.demo.userservice.entity.UserEntity;
import cl.ey.demo.userservice.security.JwtUtil;
import cl.ey.demo.userservice.service.IUserService;

class UserServiceServicesTest {
    
    private IUserService userService;
    private JwtUtil jwtUtil;
    private UserServiceServices userServiceServices;

    @BeforeEach
    void setUp() {
        userService = mock(IUserService.class);
        jwtUtil = mock(JwtUtil.class);
        userServiceServices = new UserServiceServices(userService, jwtUtil);
    }

    @Test
    void saveUser_shouldReturnUserResponseDto() {
        UserRequestDto request = UserRequestDto.builder()
                .email("test@mail.com")
                .name("Test User")
                .password("pass")
                .phones(List.of(Phone.builder().citycode("1").contrycode("56").number("1234567").build()))
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(UUID.randomUUID())
                .email("test@mail.com")
                .name("Test User")
                .password("pass")
                .inactive(false)
                .created(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .phones(Collections.emptyList())
                .build();

        when(userService.save(any(UserEntity.class), anyList())).thenReturn(userEntity);
        when(jwtUtil.generateToken(any(UserEntity.class))).thenReturn("token123");

        UserResponseDto response = userServiceServices.saveUser(request);

        assertNotNull(response);
        assertEquals("token123", response.getToken());
        assertEquals(userEntity.getId(), response.getId());
        assertEquals(userEntity.getCreated(), response.getCreated());
        assertEquals(userEntity.getLastLogin(), response.getLastLogin());
        assertEquals(userEntity.getModified(), response.getModified());
        assertEquals(userEntity.isInactive(), response.isInactive());
    }

    @Test
    void getUserById_shouldReturnUserGetRequestDto() {
        UUID id = UUID.randomUUID();
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .citycode("1")
                .contrycode("56")
                .number("1234567")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .email("test@mail.com")
                .name("Test User")
                .password("pass")
                .phones(List.of(phoneEntity))
                .build();

        when(userService.get(id)).thenReturn(userEntity);

        UserGetRequestDto dto = userServiceServices.getUserById(id);

        assertNotNull(dto);
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("Test User", dto.getName());
        assertEquals("pass", dto.getPassword());
        assertNotNull(dto.getPhones());
        assertEquals(1, dto.getPhones().size());
        assertEquals("1", dto.getPhones().get(0).getCitycode());
    }

    @Test
    void updatePassword_shouldCallUserService() {
        UUID id = UUID.randomUUID();
        PasswordUpdateDto dto = PasswordUpdateDto.builder().newPassword("newpass")
        .oldPassword("oldPass").build();

        userServiceServices.updatePassword(id, dto);

        verify(userService).updatePassword(id, dto);
    }

    @Test
    void setUserInactive_shouldCallUserService() {
        UUID id = UUID.randomUUID();

        userServiceServices.setUserInactive(id);

        verify(userService).setUserInactive(id);
    }

    @Test
    void addPhones_shouldReturnUserGetRequestDto() {
        UUID id = UUID.randomUUID();
        List<Phone> phones = List.of(Phone.builder().citycode("1").contrycode("56").number("1234567").build());

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .citycode("1")
                .contrycode("56")
                .number("1234567")
                .build();

        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .email("test@mail.com")
                .name("Test User")
                .password("pass")
                .phones(List.of(phoneEntity))
                .build();

        when(userService.addPhone(id, phones)).thenReturn(userEntity);

        UserGetRequestDto dto = userServiceServices.addPhones(id, phones);

        assertNotNull(dto);
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals(1, dto.getPhones().size());
        assertEquals("1", dto.getPhones().get(0).getCitycode());
    }

    @Test
    void deleteUser_shouldCallUserService() {
        UUID id = UUID.randomUUID();

        userServiceServices.deleteUser(id);

        verify(userService).delete(id);
    }
}
package cl.ey.demo.userservice.service.impl;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cl.ey.demo.userservice.entity.UserEntity;
import cl.ey.demo.userservice.repository.UserServiceRepository;

class UserServiceTest {

    private UserServiceRepository userServiceRepository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userServiceRepository = mock(UserServiceRepository.class);
        userService = new UserService(userServiceRepository);
    }

    @Test
    void delete_shouldDeleteUser_whenUserExists() {
        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity();
        when(userServiceRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.delete(userId);

        verify(userServiceRepository).deleteById(userId);
    }

    @Test
    void delete_shouldThrowException_whenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();
        when(userServiceRepository.findById(userId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.delete(userId));
        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userServiceRepository, never()).deleteById(any());
    }
}
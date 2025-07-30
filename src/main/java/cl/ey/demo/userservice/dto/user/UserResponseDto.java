package cl.ey.demo.userservice.dto.user;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class UserResponseDto {
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;
    private String token;
    private boolean inactive;
}


package cl.ey.demo.userservice.dto.user;

import java.util.List;

import cl.ey.demo.userservice.dto.phone.Phone;
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
public class UserGetResponse {

    private String name;
    private String email;
    private String password;
    private List<Phone> phones;
}

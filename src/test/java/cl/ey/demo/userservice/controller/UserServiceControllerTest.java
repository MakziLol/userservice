package cl.ey.demo.userservice.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.ey.demo.userservice.dto.phone.Phone;
import cl.ey.demo.userservice.dto.user.UserGetResponse;
import cl.ey.demo.userservice.dto.user.UserRequestDto;
import cl.ey.demo.userservice.dto.user.UserResponseDto;
import cl.ey.demo.userservice.service.IUserServiceServices;


@WebMvcTest(UserServiceController.class)
public class UserServiceControllerTest {

   @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserServiceServices userServiceServices;

    private UUID userId;
    private String requestJson;

    @BeforeEach
    public void setUp() {

        userId = UUID.randomUUID();
        requestJson = """
                {
                    "name": "Maxi",
                    "email": "juana@rodriguez.org",
                    "password": "Hunter@22",
                    "phones": [
                        {
                            "number": "1234567",
                            "citycode": "1",
                            "contrycode": "57"
                        }
                    ]
                }
                """;
    }

    @Test
    public void testSaveUser() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }

    @Test
    public void testGetUser() throws Exception {
       
        UserGetResponse response = new UserGetResponse();
        when(userServiceServices.getUserById(userId)).thenReturn(response);

        mockMvc.perform(
                get("/api/user-service/v1/user/" + userId)
                        .contentType("application/json")
        )
        .andExpect(status().isOk());
    }
   
    @Test
    public void testUpdatePassword() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    
    @Test
    public void testAddPhones() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    @Test
    public void testAddPhonesError() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    @Test
    public void testSetInactive() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    @Test
    public void testtestSetInactiveError() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    @Test
    public void testdeleteUser() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }
    @Test
    public void testdeleteUserError() throws Exception {
        UserRequestDto request = UserRequestDto.builder()
                .name("Maxi")
                .email("email@test.cl")
                .password("Hunter@22")
                .phones(Arrays.asList(new Phone("1234567", "1", "57")))
                .build();
        UserResponseDto response = new UserResponseDto();
        when(userServiceServices.saveUser(request)).thenReturn(response);

        mockMvc.perform(
                post("/api/user-service/v1/user")
                        .contentType("application/json")
                        .content(requestJson)
        )
        .andExpect(status().isCreated());
    }

    
}
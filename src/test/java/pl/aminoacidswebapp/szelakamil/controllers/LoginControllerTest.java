package pl.aminoacidswebapp.szelakamil.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.aminoacidswebapp.szelakamil.model.AuthRepository;
import pl.aminoacidswebapp.szelakamil.model.Authority;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginControllerTest {
    @Test
    void passingTheSameEmailAsPresentInDbShouldCauseResponseWithBadCredentialsExceptionAndResponseStatus500() {
        //given
        UserRepository repository = mock(UserRepository.class);
        given(repository.findByEmail("sample@email.com")).willReturn(prepareSampleUserList());
        LoginController controller = new LoginController(repository);
        User user = new User();
        user.setEmail("sample@email.com");
        user.setPassword("password");
        controller.setPasswordEncoder(new BCryptPasswordEncoder());
        //when + then
        ResponseEntity response = controller.registerUser(user);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("org.springframework.security.authentication.BadCredentialsException: Użytkownik o podanym adresie Email już istnieje w bazie",
                response.getBody().toString());
    }
    @Test
    void passingCorrectCredentialsShouldCauseResponseWithStatus200() {
        //given
        UserRepository repository = mock(UserRepository.class);
        AuthRepository authRepository = mock(AuthRepository.class);
        given(repository.findByEmail("sample@email.com")).willReturn(Collections.emptyList());

        User user = new User();
        user.setEmail("sample@email.com");
        user.setPassword("password");
        given(repository.save(user)).willReturn(prepareSampleUserList().get(0));
        LoginController controller = new LoginController(repository);
        controller.setPasswordEncoder(mock(BCryptPasswordEncoder.class));
        controller.setAuthRepository(authRepository);
        //when + then
        ResponseEntity response = controller.registerUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("User successfully created!", response.getBody().toString());
    }

    List<User> prepareSampleUserList() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        user.setEmail("sample@email.com");
        user.setPassword("password");
        users.add(user);
        return users;
    }
}
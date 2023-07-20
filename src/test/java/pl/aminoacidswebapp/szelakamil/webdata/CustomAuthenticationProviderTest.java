package pl.aminoacidswebapp.szelakamil.webdata;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.aminoacidswebapp.szelakamil.model.Authority;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


class CustomAuthenticationProviderTest {

    @Test
    void emptyUserNameAndPasswordShouldThrowBadCredentialsException() {
        //given
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        Authentication authentication = mock(Authentication.class);
        given(authentication.getName()).willReturn("");
        given(authentication.getCredentials()).willReturn("");
        UserRepository repository = mock(UserRepository.class);
        given(repository.findByEmail("")).willReturn(Collections.emptyList());
        provider.setRepository(repository);

        //when + then
        assertThrows(
                BadCredentialsException.class,
                () -> provider.authenticate(authentication),
                "User with passed credentials not found in the database");
    }
    @Test
    void passingIncorrectCredentialsWillThrowBadCredentialsException() {
        //given
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        Authentication authentication = mock(Authentication.class);
        given(authentication.getName()).willReturn("sample@email.com");
        given(authentication.getCredentials()).willReturn("password");
        UserRepository repository = mock(UserRepository.class);
        given(repository.findByEmail("sample@email.com")).willReturn(getSampleUser());
        provider.setRepository(repository);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        provider.setPasswordEncoder(encoder);
        //when + then
        assertThrows(
                BadCredentialsException.class,
                () -> provider.authenticate(authentication),
                "Incorrect password!");

    }
    @Test
    void passingRightCredentialsShouldReturnUsernamePasswordAuthenticationToken() {
        //given
        CustomAuthenticationProvider provider = new CustomAuthenticationProvider();
        Authentication authentication = mock(Authentication.class);
        given(authentication.getName()).willReturn("sample@email.com");
        given(authentication.getCredentials()).willReturn("pwd");
        UserRepository repository = mock(UserRepository.class);
        given(repository.findByEmail("sample@email.com")).willReturn(getSampleUser());
        provider.setRepository(repository);
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        provider.setPasswordEncoder(encoder);
        //when
        Authentication token = provider.authenticate(authentication);
        //then
        assertThat(token.getClass().getCanonicalName(), equalTo(UsernamePasswordAuthenticationToken.class.getCanonicalName()));
    }

    List<User> getSampleUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("sample@email.com");
        user.setPassword("$2a$12$8PmylIf6YLwis.t.iqqQJeo8jDZvn/K4g3OClwuvTWLpg8l3oqO1.");
        List usersList = new ArrayList();
        user.setAuthorities(getSampleAuthorities());
        usersList.add(user);
        return usersList;
    }
    List<Authority> getSampleAuthorities() {
        Authority auth = new Authority();
        auth.setId(1);
        auth.setName("ADMIN");
        User user = new User();
        user.setId(1);
        auth.setUser(user);
        List authorities = new ArrayList();
        authorities.add(auth);
        return authorities;
    }
}
package pl.aminoacidswebapp.szelakamil.webdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.aminoacidswebapp.szelakamil.model.Authority;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String passedPwd = authentication.getCredentials().toString();
        List<User> users = repository.findByEmail(username).stream().toList();
        if (!users.isEmpty()){
            if(passwordEncoder.matches(passedPwd, users.get(0).getPassword())){
                return new UsernamePasswordAuthenticationToken(username, passedPwd, getAuthorities(users.get(0).getAuthorities()));
            } else {
                throw new BadCredentialsException("Incorrect password!");
            }
        } else {
            throw new BadCredentialsException("User with passed credentials not found in the database");
        }
    }

    public List<GrantedAuthority> getAuthorities(List<Authority> authorities) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(Authority authority : authorities){
            authorityList.add(new SimpleGrantedAuthority(authority.getName()));
        }
        return authorityList;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

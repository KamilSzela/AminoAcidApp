package pl.aminoacidswebapp.szelakamil.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.aminoacidswebapp.szelakamil.model.AuthRepository;
import pl.aminoacidswebapp.szelakamil.model.Authority;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {

    UserRepository repository;
    @Autowired
    AuthRepository authRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/login")
    String returnLoginPage() {
        return "login";
    }
    @PostMapping(value = "/register")
    public ResponseEntity<String> registerUser(@RequestBody User userData){
        User savedCustomer = null;
        ResponseEntity response = null;
        try{
            String hashedPwd = passwordEncoder.encode(userData.getPassword());
            userData.setPassword(hashedPwd);

            savedCustomer = repository.save(userData);

            Authority authority = new Authority();
            authority.setUser(savedCustomer);
            authority.setName("ROLE_USER");

            authRepository.save(authority);
            if(savedCustomer.getId() > 0) {
                response = ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body("User successfully created!");
            }
        } catch (Exception ex) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error occurred" + ex.getMessage());
        }
        return response;
    }

}

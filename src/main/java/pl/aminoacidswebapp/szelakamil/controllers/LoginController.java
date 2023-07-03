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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.aminoacidswebapp.szelakamil.model.User;
import pl.aminoacidswebapp.szelakamil.model.UserRepository;

import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public LoginController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/login")
    String returnLoginPage() {
        return "login";
    }

    @ResponseBody
    @RequestMapping(value = "/logUserIn", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<String> logUserIn(@RequestBody User userData) {
        String answer = "Poprawnie zalogowano użytkownika. Nastąpi przekierowanie do strony głównej.";
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(gson.toJson(answer));
    }

}

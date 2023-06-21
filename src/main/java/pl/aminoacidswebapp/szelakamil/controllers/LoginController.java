package pl.aminoacidswebapp.szelakamil.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.aminoacidswebapp.szelakamil.model.User;

import java.nio.charset.StandardCharsets;

@Controller
public class LoginController {

    @RequestMapping("/login")
    String returnLoginPage() {
        return "login";
    }

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @ResponseBody
    @RequestMapping(value = "/newUser", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<String> createNewUser(@RequestBody User userData) {
        String answer = "Poprawnie zalogowano użytkownika. Nastąpi przekierowanie do strony głównej.";
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(gson.toJson(answer));
    }
}

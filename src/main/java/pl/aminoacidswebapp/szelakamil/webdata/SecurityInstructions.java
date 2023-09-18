package pl.aminoacidswebapp.szelakamil.webdata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityInstructions {

    @Bean
    SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().
                authorizeHttpRequests((requests) -> {
                    requests.requestMatchers("/",
                            "/composeMeal",
                            "/requirements",
                            "/commonIngredients",
                            "/commonIngredients/*",
                            "/img/*",
                            "/css/*",
                            "/login",
                            "/register",
                            "/getMeals").permitAll();
                    requests.requestMatchers("/savedMeals", "/newMeal").hasAnyRole("USER", "ADMIN");
                })
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/")
                .permitAll();
        http.logout().logoutSuccessUrl("/");
        http.headers().frameOptions().disable();
        http.httpBasic();
        return http.build();
    }

    @Bean
    public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

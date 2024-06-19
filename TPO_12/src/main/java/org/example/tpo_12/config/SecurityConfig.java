package org.example.tpo_12.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/register/**", "/confirm").permitAll()
                //.requestMatchers("/userPage").hasAnyRole("READER", "PUBLISHER", "LIBRARIAN", "ADMIN")
                .requestMatchers("/images/background.png", "/images/logo.png").permitAll()
                .requestMatchers("/adminPage/**").hasAnyRole("ADMIN")
                .requestMatchers("/book").hasAnyRole("READER", "PUBLISHER", "LIBRARIAN", "ADMIN")
                .requestMatchers("/addBook").hasAnyRole("PUBLISHER", "LIBRARIAN", "ADMIN")
                .requestMatchers("/borrowBook").hasAnyRole("READER", "PUBLISHER", "LIBRARIAN", "ADMIN")
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated());
        http.formLogin(login -> login.loginPage("/login").permitAll());
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .logoutSuccessUrl("/"));
        http.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()));
        http.headers(config -> config.frameOptions(options -> options.sameOrigin()));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers("/images/background.png", "/images/logo.png");
    }
}

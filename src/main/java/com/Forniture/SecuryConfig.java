package com.Forniture;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuryConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/",
                        "/Home/**",
                        "/sala/**",
                        "/butacas/**",
                        "/comedores/**",
                        "/armarios/**",
                        "/dormitorios/**",
                        "/producto/**", 
                        "/login",
                        "/loginError",
                        "/css/**",
                        "/js/**",
                        "/img/**",
                        "/SVG/**",
                        "/Media/**",
                        "/webjars/**"
                ).permitAll()
                .requestMatchers("/managementProduct/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/loginError")
                .permitAll()
                );

        return http.build();
    }

}

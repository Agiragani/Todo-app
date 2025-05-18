//package com.java.Todo.app.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  // disable CSRF for testing APIs easily
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().authenticated()  // all requests require auth
//                )
//                .httpBasic(Customizer.withDefaults());  // enable HTTP Basic authentication
//
//        return http.build();
//    }
//}
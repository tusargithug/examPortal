package com.examportal.examPortal.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    //Authentication
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        //It is for static user name and password
//        UserDetails admin = User.withUsername("Tusar")
//                .password(passwordEncoder.encode("Tusar@123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("Banty")
//                .password(passwordEncoder.encode("Banty@123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
        return new UserInfoUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //In blank space to be added public api
        return http.csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers("employee/save")
                .permitAll()
                .and()
                .authorizeHttpRequests()
                //In blank space we have to add restrict api
                .requestMatchers("employee/get")
                .authenticated()
                .and()
                .formLogin()
                .and()
                .build();
    }
}

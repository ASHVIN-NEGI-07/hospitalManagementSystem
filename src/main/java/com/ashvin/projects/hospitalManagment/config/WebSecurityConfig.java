package com.ashvin.projects.hospitalManagment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final PasswordEncoder passwordEncoder;

    // no autherization told , so all requests are now publicly accessible
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")   // Only ADMIN role can access /admin/**
                        .requestMatchers("/doctors/**").hasAnyRole("DOCTOR","ADMIN") // DOCTOR and ADMIN roles can access /doctors/**
                )
                .formLogin(Customizer.withDefaults());  // Enable form-based login

        return httpSecurity.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("admin")
                .password(passwordEncoder.encode("pass"))
                .roles("ADMIN")
                .build();

        UserDetails user2 = User.withUsername("patient")
                .password(passwordEncoder.encode("pass"))
                .roles("PATIENT")
                .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

}

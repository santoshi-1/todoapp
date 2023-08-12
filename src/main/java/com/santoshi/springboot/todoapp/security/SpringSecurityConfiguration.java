package com.santoshi.springboot.todoapp.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.function.Function;

@Configuration
public class SpringSecurityConfiguration {


    @Bean
    public InMemoryUserDetailsManager configureUserDetails() {
        UserDetails userDetails1 = createNewUser("Santoshi", "Dummy@123");
        UserDetails userDetails2 = createNewUser("Jyothi", "Pass@123");
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        return User.builder().passwordEncoder(passwordEncoder).username(username).password(password).roles("USER", "ADMIN").build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Features of spring security -
     * 1. All URL's are protected
     * 2. A login form is shown for un-authorized requests
     * <p>
     * Add additional functionality -
     * 1. CSRF disabled
     * 2. Frames
     */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        /**
         * ensuring all requests are authenticated.
         */
        httpSecurity.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        httpSecurity.formLogin(Customizer.withDefaults());

        /**
         * disabling csrf and enabling use of frames in the application
         */

        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.headers(frameOptions -> frameOptions.disable());
        return httpSecurity.build();
    }

}

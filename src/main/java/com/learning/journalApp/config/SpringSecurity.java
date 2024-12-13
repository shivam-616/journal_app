package com.learning.journalApp.config;

import com.learning.journalApp.service.UserDetailServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http.authorizeHttpRequests(request -> request
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/journal/**","/user/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN"))
                .httpBasic(Customizer.withDefaults())
                .build();


    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(userDetailServiceImp).passwordEncoder(passwordEncoder());
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}



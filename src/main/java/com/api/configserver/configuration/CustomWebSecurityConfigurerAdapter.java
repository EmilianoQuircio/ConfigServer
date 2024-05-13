package com.api.configserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfigurerAdapter {

    @Value("${sicurezza.userpwd}")
    String user_password;

    @Value("${sicurezza.adminpwd}")
    String admin_password;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
            .withUser("user")
            .password(passwordEncoder().encode(user_password))
            .roles("USER")
            .and()
            .withUser("admin")
            .password(passwordEncoder().encode(admin_password))
            .roles("USER","ACTUATOR");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/actuator/**").hasRole("ACTUATOR")
            .anyRequest().authenticated()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler());
        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){return new CustomAccessDeniedHandler();}
    @Bean
    public static PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


}

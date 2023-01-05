package com.webshopapp.adminpanel.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String ADMIN = "Admin";

    private static final String SUPPLIER = "Supplier";

    @Bean
    public UserDetailsService userDetailsService() {
        return new SecurityUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/users/**").hasAnyAuthority(ADMIN)
                .antMatchers("/categories/**", "/brands/**").hasAnyAuthority(ADMIN)
                .antMatchers("/products/**").hasAnyAuthority(ADMIN, SUPPLIER)
                .antMatchers("/customers/**").hasAnyAuthority(ADMIN, SUPPLIER)
                .antMatchers("/orders/**").hasAnyAuthority(ADMIN, SUPPLIER)
                .antMatchers("/reports/**").hasAnyAuthority(ADMIN, SUPPLIER)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }
}
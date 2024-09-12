package com.springmaniya.springmaniya.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
public class AppConfig {


//    @Autowired
//    CorsConfiguration cfg;
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeHttpRequests(Authorize->Authorize.requestMatchers("/api/**").authenticated()
//                        .anyRequest().permitAll()
//                ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//                .csrf().disable()
//                .cors().configurationSource(corsConfigurationSource()).and()
//                .httpBasic().and().formLogin();
//
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/auth/**").permitAll() // Allow public access to signup/signin endpoints
                        .requestMatchers("/api/**").authenticated() // Require authentication for API endpoints
                        .anyRequest().permitAll() // Allow other routes
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF (since you're not using cookies/session)
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Apply CORS configuration
                .httpBasic(httpBasic -> httpBasic.disable()) // Disable basic HTTP authentication
                .formLogin(formLogin -> formLogin.disable()); // Disable default login form

        return http.build();
    }



    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
               CorsConfiguration cfg = new CorsConfiguration();

                // Allow the front-end at 'http://localhost:3000' to access the API
                cfg.setAllowedOrigins(Arrays.asList("http://localhost:3000"));

                // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.)
                cfg.setAllowedMethods(Collections.singletonList("*"));

                // Allow credentials (cookies, authorization headers, etc.) in cross-origin requests
                cfg.setAllowCredentials(true);

                // Allow all headers in the request
                cfg.addAllowedHeader("*");

                // Expose the 'Authorization' header to the front-end
                cfg.setExposedHeaders(Arrays.asList("Authorization"));

                // Cache the CORS configuration for 1 hour (3600 seconds)
                cfg.setMaxAge(3600L);

                return cfg;
            }
        };
    }

    //password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}

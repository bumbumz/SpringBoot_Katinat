package com.phamcongvinh.testusser.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final AuthenticationFilter authenticationFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .cors(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests((authorize) -> authorize
                                                .requestMatchers("/api/auth/**").permitAll()
                                                .requestMatchers("/api/users/**").permitAll()
                                                .requestMatchers("/api/categories/**").permitAll()
                                                .requestMatchers("/api/product/**").permitAll()
                                                .requestMatchers("/api/topic/**").permitAll()
                                                .requestMatchers("/api/post/**").permitAll()
                                                .requestMatchers("/api/order/**").permitAll()
                                                .requestMatchers("/api/cartdetail/**").permitAll()
                                                .requestMatchers("/api/product-sale/**").permitAll()
                                                .requestMatchers("/api/orders/**").permitAll()
                                                .requestMatchers("/api/order-details/**").permitAll()
                                                .requestMatchers("/img/**").permitAll()
                                                .requestMatchers("/api/img/{name}").permitAll()
                                                .requestMatchers("/api/must-try/**").permitAll()
                                                .requestMatchers("/api/rock/**").permitAll()
                                                .requestMatchers("/api/sugar/**").permitAll()
                                                .requestMatchers("/api/product-sugar/**").permitAll()
                                                .requestMatchers("/api/product-rock/**").permitAll()
                                                .requestMatchers("/api/news-event/**").permitAll()
                                                .requestMatchers("/api/wishlist/**").permitAll()
                                                .requestMatchers("/api/email/**").permitAll()
                                                .requestMatchers("/api/vnpayment/**").permitAll()

                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                                .httpBasic(Customizer.withDefaults());
                return http.build();
        }
}
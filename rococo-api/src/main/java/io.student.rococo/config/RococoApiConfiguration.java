package io.student.rococo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@EnableWebSecurity
@Configuration
public class RococoApiConfiguration {

    /**
     * Публичная цепочка безопасности - для endpoints, доступных без аутентификации
     * ОБРАБАТЫВАЕТ ТОЛЬКО /api/session
     */
    @Bean
    @Order(1)
    public SecurityFilterChain publicSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Ключевое изменение: используем antMatcher для точного указания
                .securityMatcher("/api/session")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                // Явно говорим, что НЕ используем OAuth2 здесь
                .oauth2ResourceServer(oauth2 -> oauth2.disable())
                .sessionManagement(session -> session.disable())
                .build();
    }

    /**
     * Защищенная цепочка безопасности - для ВСЕХ ОСТАЛЬНЫХ endpoints
     */
    @Bean
    @Order(2)
    public SecurityFilterChain protectedSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Обрабатываем ВСЕ запросы, КРОМЕ /api/session
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                )
                .build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        objectMapper.setDateFormat(df);
        return objectMapper;
    }

}
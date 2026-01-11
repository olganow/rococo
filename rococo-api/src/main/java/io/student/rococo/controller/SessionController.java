package io.student.rococo.controller;

import io.student.rococo.model.SessionJson;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
@RestController
@RequestMapping("/api/session")
public class SessionController {

    /**
     * Endpoint для получения информации о сессии.
     * Работает как с аутентифицированными, так и с анонимными пользователями.
     *
     * @param jwt объект JWT (будет null для анонимных пользователей)
     * @param authHeader заголовок Authorization (для отладки)
     * @return информация о сессии
     */
    @GetMapping
    public SessionJson getSession(
            @AuthenticationPrincipal Jwt jwt,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        if (jwt != null) {
            // Аутентифицированный пользователь
            return new SessionJson(
                    jwt.getClaimAsString("preferred_username"),  // Имя из токена
                    Date.from(jwt.getIssuedAt()),                // Время выдачи токена
                    Date.from(jwt.getExpiresAt())                // Время истечения токена
            );
        } else {
            // Анонимный пользователь (или без валидного токена)
            return new SessionJson(
                    "Anonymous",                    // Заглушка для анонимных
                    new Date(),                     // Текущее время
                    Date.from(Instant.now().plusSeconds(3600))  // +1 час
            );
        }
    }
}

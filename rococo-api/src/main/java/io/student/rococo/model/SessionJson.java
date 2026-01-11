package io.student.rococo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record SessionJson(

        @NotNull(message = "Username cannot be null")
        String username,

        @NotNull(message = "IssuedAt cannot be null")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date issuedAt,

        @NotNull(message = "ExpiresAt cannot be null")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        Date expiresAt

) {

    /**
     * Проверяет, активна ли сессия
     */
    public boolean isActive() {
        Date now = new Date();
        return now.after(issuedAt) && now.before(expiresAt);
    }

    /**
     * Возвращает оставшееся время жизни сессии в секундах
     */
    public long getRemainingSeconds() {
        Date now = new Date();
        long diff = expiresAt.getTime() - now.getTime();
        return diff / 1000;
    }
}
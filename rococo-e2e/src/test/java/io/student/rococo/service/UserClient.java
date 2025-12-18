package io.student.rococo.service;

import io.student.rococo.model.UserJson;

import java.util.Optional;

public interface UserClient {
    /*Создает нового пользователя
    Принимает логин и пароль
    Возвращает созданного пользователя в формате*/

    UserJson createUser(String username, String password);

   /* Ищет пользователя по имени
    Возвращает Optional<UserJson> - это безопасный способ вернуть "может быть есть, а может и нет"*/
    Optional<UserJson> findByUsername(String username);
}

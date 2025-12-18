package io.student.rococo.service;

import io.student.rococo.config.Config;
import io.student.rococo.model.UserJson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.DriverManager;
import java.util.Optional;
import java.util.UUID;

public class UsersDbClient implements UserClient {

    private static final Config CFG = Config.getInstance();

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public UserJson createUser(String username, String password) {
        try {
            //Создается один UUID для всех запросов (как требовалось)
            //Конвертируется в UUID объект для UserJson
            final String userId = UUID.randomUUID().toString();
            final UUID userUuid = UUID.fromString(userId);
            //Используется passwordEncoder.encode(password) (как требовалось)
            final String encodedPassword = passwordEncoder.encode(password);


                final JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(
                        DriverManager.getConnection(
                                CFG.userJdbcUrl(),
                                CFG.dbUsername(),
                                CFG.dbPassword()
                        ),
                        true)
                );

                // Вставляем пользователя в таблицу user
            //Используется UUID_TO_BIN(?, true) для конвертации UUID (как требовалось)
            //
            //Все boolean поля устанавливаются в true (как в UserService)
            //Пароль вставляется в зашифрованном виде
            jdbcTemplate.update(
                    "INSERT INTO `user` (id, username, password, enabled, account_non_expired, account_non_locked, credentials_non_expired) " +
                            "VALUES (UUID_TO_BIN(?, true), ?, ?, true, true, true, true)",
                    userId, username, encodedPassword
            );

            // Вставляем authority 'read' для пользователя
            //Используется тот же userId (как требовалось)
            // Создаются обе authorities: 'read' и 'write' (как в UserService)
            jdbcTemplate.update(
                    "INSERT INTO authority (user_id, authority) VALUES (UUID_TO_BIN(?, true), 'read')",
                    userId
            );

            // Вставляем authority 'write' для пользователя
            jdbcTemplate.update(
                    "INSERT INTO authority (user_id, authority) VALUES (UUID_TO_BIN(?, true), 'write')",
                    userId
            );

            //Создается UserJson с доступными данными
            return new UserJson(
                    userUuid,    // id
                    username,    // username
                    null,        // firstname
                    null,        // lastname
                    null         // avatar
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Используется BIN_TO_UUID для конвертации обратно из бинарного формата
    //Возвращает Optional<UserJson> как указано в интерфейсе
    //Если пользователь не найден, возвращает Optional.empty()
    @Override
    public Optional<UserJson> findByUsername(String username) {
        try {
            final JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(
                    DriverManager.getConnection(
                            CFG.userJdbcUrl(),
                            CFG.dbUsername(),
                            CFG.dbPassword()
                    ),
                    true)
            );
            // Извлекаем пользователя из базы данных
            String sql = "SELECT BIN_TO_UUID(id) as uuid, username FROM `user` WHERE username = ?";

            UserJson user = jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                            new UserJson(
                                    UUID.fromString(rs.getString("uuid")),
                                    rs.getString("username"),
                                    null,  // firstname
                                    null,  // lastname
                                    null   // avatar
                            ),
                    username
            );

            return Optional.ofNullable(user);
        } catch (Exception e) {
            // Если пользователь не найден, возвращаем пустой Optional
            return Optional.empty();
        }
    }
}
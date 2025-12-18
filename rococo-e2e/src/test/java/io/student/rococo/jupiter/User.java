package io.student.rococo.jupiter;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static io.student.rococo.constants.Constants.USER_PASSWORD;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(io.student.rococo.jupiter.CreateUserExtension.class)
public @interface User {
    String password() default USER_PASSWORD;
}

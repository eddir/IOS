package рф.пинж.ios.permission;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация определяет право необходимое для использования объектом пользователем
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {

    String value();

}

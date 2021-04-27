package рф.пинж.ios.controller;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Аннотация для методов в контроллерах. Определяет какой метод будет вызван при вызванной ссылке.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface URL {

    String value();

}

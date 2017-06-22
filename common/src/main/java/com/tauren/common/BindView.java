package com.tauren.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author AwesomeJim  E-MAIL:AwesomeJim@foxmail.com
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int id();

    boolean click() default false;
}

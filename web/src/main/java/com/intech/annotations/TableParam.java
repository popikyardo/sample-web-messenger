package com.intech.annotations;

import java.lang.annotation.*;

/**
 * Created by popikyardo on 22.07.15.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableParam {
}

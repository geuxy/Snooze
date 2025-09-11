package gg.snooze.systems.module.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleData {

    String name();
    String note() default "No Description";
    ModuleType type();

    boolean enabled() default false;
    int keyCode() default 0;

}

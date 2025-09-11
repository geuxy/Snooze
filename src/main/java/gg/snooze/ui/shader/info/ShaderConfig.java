package gg.snooze.ui.shader.info;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShaderConfig {

    String frag();
    String vertex();

}

package gg.snooze.render.shader;

import gg.snooze.core.Snooze;
import gg.snooze.render.shader.info.ShaderConfig;
import gg.snooze.render.shader.info.ShaderData;
import gg.snooze.util.FileUtil;
import lombok.Getter;
import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.Resource;
import net.minecraft.util.Identifier;

import java.io.InputStream;
import java.util.function.Consumer;

import static org.lwjgl.opengl.GL46.*;

@Getter
public abstract class AbstractShader<P> {

    private final ShaderData data;

    private final int program;

    public AbstractShader() {
        ShaderConfig config = this.getClass().getAnnotation(ShaderConfig.class);
        if(config == null) throw new NullPointerException("Shader MUST HAVE annotation");

        this.data = new ShaderData(config.frag(), config.vertex());
        this.program = this.initShader(config.frag(), config.vertex());

        if(glGetProgrami(program, GL_LINK_STATUS) == 0) {
            throw new RuntimeException("Failed to load shader '" + this.data.frag() + "'");
        }
    }

    public abstract void render(P params);

    private int initShader(String frag, String vertex) {
        int program = glCreateProgram();
        int fragShader = getShader(frag, GL_FRAGMENT_SHADER);
        int vertexShader = getShader(vertex, GL_VERTEX_SHADER);

        glAttachShader(program, fragShader);
        glAttachShader(program, vertexShader);
        glLinkProgram(program);

        glDeleteShader(fragShader);
        glDeleteShader(vertexShader);

        return program;
    }

    private int getShader(String fileName, int type) {
        try {
            MinecraftClient client = MinecraftClient.getInstance();

            Identifier identifier = Identifier.of(Snooze.MOD_ID, "shaders/" + fileName);
            Resource resource = client.getResourceManager().getResource(identifier).orElse(null);

            if (resource == null) {
                Snooze.LOGGER.error("Failed to locate shader: {}", identifier);
                return 0;
            }

            String shaderSource;
            try (InputStream is = resource.getInputStream()) {
                shaderSource = FileUtil.read(is);
            }

            int shader = glCreateShader(type);
            glShaderSource(shader, shaderSource);
            glCompileShader(shader);

            if (glGetShaderi(shader, GL_COMPILE_STATUS) == GL_FALSE) {
                String log = glGetShaderInfoLog(shader);
                Snooze.LOGGER.error("Unable to compile shader: {}", fileName);
                Snooze.LOGGER.error(log);
                glDeleteShader(shader);
                return 0;
            }

            return shader;

        } catch (Exception e) {
            Snooze.LOGGER.error("Unable to load shader: {}", fileName);
            e.printStackTrace();
            return 0;
        }
    }

    public void useShader(Runnable runnable) {
        glUseProgram(this.program);
        runnable.run();
        glUseProgram(0);
    }

    public void uniform(String name, int a) {
        int location = glGetUniformLocation(program, name);
        glUniform1i(location, a);
    }

    public void uniform(String name, int a, int b) {
        int location = glGetUniformLocation(program, name);
        glUniform2i(location, a, b);
    }

    public void uniform(String name, int a, int b, int c) {
        int location = glGetUniformLocation(program, name);
        glUniform3i(location, a, b, c);
    }

    public void uniform(String name, int a, int b, int c, int d) {
        int location = glGetUniformLocation(program, name);
        glUniform4i(location, a, b, c, d);
    }

    public void uniform(String name, float a) {
        int location = glGetUniformLocation(program, name);
        glUniform1f(location, a);
    }

    public void uniform(String name, float a, float b) {
        int location = glGetUniformLocation(program, name);
        glUniform2f(location, a, b);
    }

    public void uniform(String name, float a, float b, float c) {
        int location = glGetUniformLocation(program, name);
        glUniform3f(location, a, b, c);
    }

    public void uniform(String name, float a, float b, float c, float d) {
        int location = glGetUniformLocation(program, name);
        glUniform4f(location, a, b, c, d);
    }

}

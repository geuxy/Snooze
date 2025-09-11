package gg.snooze.ui.shader.impl;

import gg.snooze.ui.shader.AbstractShader;
import gg.snooze.ui.shader.info.ShaderConfig;
import net.minecraft.client.MinecraftClient;

@ShaderConfig(frag = "example.glsl", vertex = "vertex.glsl")
public class ExampleShader extends AbstractShader<ExampleShader.Params> {

    public record Params(int x, int y, int width, int height, int color) {}

    @Override
    public void render(Params params) {
        MinecraftClient client = MinecraftClient.getInstance();

        this.useShader(() -> {
            int scale = client.getWindow().getScaleFactor();
            float scaledX = params.x * scale;
            float scaledY = (client.getWindow().getHeight() - (params.height * scale)) - (params.y * scale);
            float scaledWidth = params.width * scale;
            float scaledHeight = params.height * scale;

            this.uniform("position", scaledX, scaledY);
            this.uniform("resolution", scaledWidth, scaledHeight);
        });
    }

}

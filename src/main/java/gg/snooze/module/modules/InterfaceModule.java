package gg.snooze.module.modules;

import gg.snooze.Snooze;
import gg.snooze.event.Listener;
import gg.snooze.event.events.ModuleToggleEvent;
import gg.snooze.event.events.RenderHudEvent;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.*;

/*
 * Example HUD for now, will add draggable system soon to replace this
 */
@ModuleData(name = "Interface", note = "Client HUD", type = ModuleType.RENDER)
public class InterfaceModule extends Module {

    private static final int WATERMARK_COLOR = -1;
    private static final int ARRAYLIST_COLOR = -1;
    private static final int ARRAYLIST_BACKGROUND = 0x60000000;

    private final List<ModuleRenderable> enabledModules = new ArrayList<>();
    private final Map<Module, ModuleRenderable> renderableCache = new HashMap<>();

    record ModuleRenderable(Component text, int textLength) {}

    public InterfaceModule() {
        super();
        this.addListener(RenderHudEvent.ID, onRenderHud);
        this.addListener(ModuleToggleEvent.ID, onModuleToggle);
    }

    @Override
    public String getSuffix() {
        return "Test";
    }

    @Override
    public void onEnable() {
        this.enabledModules.clear();
        this.renderableCache.clear();
        Snooze.INSTANCE.modules.findAll(m -> m.getConfig().isEnabled()).forEach(this::resolveModule);
    }

    @Override
    public void onDisable() {
        this.enabledModules.clear();
        this.renderableCache.clear();
    }

    private final Listener<ModuleToggleEvent> onModuleToggle = event -> {
        this.resolveModule(event.getModule());
    };

    private final Listener<RenderHudEvent> onRenderHud = event -> {
        drawWatermark(event);
        drawArraylist(event);
    };

    private void drawWatermark(RenderHudEvent event) {
        event.getGraphics().text(Minecraft.getInstance().font, Snooze.NAME, 4, 4, WATERMARK_COLOR);
    }

    private void drawArraylist(RenderHudEvent event) {
        Font font = Minecraft.getInstance().font;
        int endX = event.getGraphics().guiWidth();

        int moduleY = 0;
        int moduleHeight = 12;
        for(ModuleRenderable renderable : this.enabledModules) {
            int textX = endX - renderable.textLength() - 2;

            event.getGraphics().fill(textX - 2, moduleY, endX, moduleY + moduleHeight, ARRAYLIST_BACKGROUND);

            event.getGraphics().text(
                    font,
                    renderable.text(),
                    textX,
                    moduleY + (moduleHeight / 2) - (font.lineHeight / 2),
                    ARRAYLIST_COLOR
            );

            moduleY += moduleHeight;
        }
    }

    private void resolveModule(Module module) {
        ModuleRenderable renderable = this.renderableCache.computeIfAbsent(module,
                m -> createModuleRenderable(m, Minecraft.getInstance().font)
        );

        if(module.getConfig().isEnabled()) {
            this.enabledModules.add(renderable);
            this.enabledModules.sort(Comparator.comparingDouble(ModuleRenderable::textLength).reversed());

        } else {
            this.enabledModules.remove(renderable);
        }
    }

    private ModuleRenderable createModuleRenderable(Module module, Font font) {
        Component component = Component.literal(module.getMetadata().name());

        if(module.getSuffix() != null) {
            MutableComponent suffixComponent = Component.literal(" ")
                    .append(module.getSuffix())
                    .withStyle(ChatFormatting.GRAY);

            component = component.copy().append(suffixComponent);
        }

        return new ModuleRenderable(component, font.width(component.getString()));
    }

}

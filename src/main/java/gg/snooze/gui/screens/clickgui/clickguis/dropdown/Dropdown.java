package gg.snooze.gui.screens.clickgui.clickguis.dropdown;

import gg.snooze.core.Snooze;
import gg.snooze.gui.elements.UIDragElement;
import gg.snooze.gui.elements.UIElement;
import gg.snooze.gui.screens.clickgui.elements.CategoryElement;
import gg.snooze.gui.screens.clickgui.elements.ModuleElement;
import gg.snooze.gui.screens.clickgui.theme.ClickGuiTheme;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleType;
import gg.snooze.util.MouseUtil;
import net.minecraft.client.gui.DrawContext;

import java.util.List;

public class Dropdown extends UIDragElement {

    private final CategoryElement category;
    private final UIElement moduleArea;
    private final ModuleElement[] modules;

    private final int componentHeight;
    private final int maxHeight;

    private double totalHeight;

    private final ClickGuiTheme theme;

    public Dropdown(ClickGuiTheme theme, ModuleType type, int x, int y, int width, int height, int componentHeight) {
        this.theme = theme;
        this.maxHeight = height;
        this.setPosition(x, y);
        this.setSize(width, height);
        this.componentHeight = componentHeight;

        List<Module> modules = Snooze.INSTANCE.modules().findAll(m -> m.getMetadata().type() == type);

        this.category = new CategoryElement(type);
        this.modules = new ModuleElement[modules.size()];

        for(int i = 0; i < modules.size(); i++) {
            this.modules[i] = new ModuleElement(modules.get(i));
            this.modules[i].setSize(width, componentHeight);
        }

        this.moduleArea = new UIElement();
        this.updateComponents();
    }

    public void render(DrawContext context, double mouseX, double mouseY) {
        this.updateComponents();

        this.animateDrag(mouseX, mouseY);

        this.theme.renderClickGui(context, this, mouseX, mouseY);
        this.theme.renderModuleArea(context, this.moduleArea, mouseX, mouseY);
        this.theme.renderCategoryArea(context, this.category, mouseX, mouseY);
        this.theme.renderCategory(context, this.category, mouseX, mouseY);

        for(ModuleElement module : this.modules) {
            this.theme.renderModule(context, module, mouseX, mouseY);
            module.renderProperties(context, theme, mouseX, mouseY);
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(MouseUtil.isMouseOver(mouseX, mouseY, this.category) && button == 0) {
            this.startDrag(mouseX, mouseY);
            return true;
        }

        if(MouseUtil.isMouseOver(mouseX, mouseY, this.moduleArea)) {
            for(ModuleElement element : this.modules) {
                if(MouseUtil.isMouseOver(mouseX, mouseY, element)
                && element.mouseClicked(mouseX, mouseY, button)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(button == 0) this.stopDrag();

        for(ModuleElement element : this.modules) {
            element.mouseReleased(mouseX, mouseY, button);
        }
    }

    public void updateComponents() {
        this.category.setPosition(x, y);
        this.category.setSize(width, componentHeight);

        double moduleY = this.componentHeight;
        for(ModuleElement module : this.modules) {
            module.setPosition(x, y + moduleY);
            module.setSize(width, componentHeight);
            module.updateProperties();
            moduleY += module.getHeight();
        }

        this.totalHeight = moduleY;

        this.setSize(width, totalHeight);

        this.moduleArea.setPosition(x, y + componentHeight);
        this.moduleArea.setSize(width, moduleY - componentHeight);
    }

}

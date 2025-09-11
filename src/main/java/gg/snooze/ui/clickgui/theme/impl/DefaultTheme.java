package gg.snooze.ui.clickgui.theme.impl;

import gg.snooze.ui.framework.UIElement;
import gg.snooze.ui.clickgui.elements.CategoryElement;
import gg.snooze.ui.clickgui.elements.ModuleElement;
import gg.snooze.ui.clickgui.elements.properties.*;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import gg.snooze.ui.clickgui.theme.ClickGuiTheme;
import gg.snooze.util.INameable;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;

public class DefaultTheme implements ClickGuiTheme {

    private final TextRenderer font = MinecraftClient.getInstance().textRenderer;

    private static final int CATEGORY_BG = new Color(35, 38, 52).getRGB();
    private static final int MODULE_BG = new Color(48, 52, 70).getRGB();
    private static final int PROPERTY_BG = CATEGORY_BG;
    private static final int LIGHT_FOREGROUND = new Color(198, 208, 245).getRGB();
    private static final int DARK_FOREGROUND = new Color(131, 139, 167).getRGB();
    private static final int ACCENT = new Color(140, 170, 238).getRGB();

    private static final String EXPAND_CHAR = ">";
    private static final String COLLAPSE_CHAR = "<";

    private static final int SETTING_PADDING = 2;
    private static final int DROP_BORDER_SIZE = 2;

    @Override
    public void renderClickGui(DrawContext context, UIElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);

        context.fill(pos[0] - DROP_BORDER_SIZE, pos[1] - DROP_BORDER_SIZE, pos[2] + DROP_BORDER_SIZE, pos[3] + DROP_BORDER_SIZE, CATEGORY_BG);
    }

    @Override
    public void renderCategoryArea(DrawContext context, UIElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);

        context.fill(pos[0], pos[1], pos[2], pos[3], CATEGORY_BG);
    }

    @Override
    public void renderModuleArea(DrawContext context, UIElement area, double mouseX, double mouseY) {
    }

    @Override
    public void renderPropertyArea(DrawContext context, UIElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);

        context.fill(pos[0], pos[1], pos[2], pos[3], PROPERTY_BG);
        //context.fill((int) area.getX() + 1, (int) area.getY(), (int) area.getX() + 2, (int) (area.getY() + area.getHeight()), ACCENT);
    }

    @Override
    public void renderCategory(DrawContext context, CategoryElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));

        context.drawCenteredTextWithShadow(font, element.getType().getName(), pos[4], fontCenterY, LIGHT_FOREGROUND);
    }

    @Override
    public void renderModule(DrawContext context, ModuleElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getOriginalHeight(), 0, 0);

        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2D));
        int bgColor = element.getModule().getConfig().isEnabled() ? ACCENT : MODULE_BG;

        context.fill(pos[0], pos[1], pos[2], pos[3], bgColor);
        context.drawCenteredTextWithShadow(font, element.getModule().getMetadata().name(), pos[4], fontCenterY, LIGHT_FOREGROUND);

        if (!element.getModule().getProperties().isEmpty()) {
            String arrowText = element.getPropertyArea() != null ? COLLAPSE_CHAR : EXPAND_CHAR;

            context.drawText(font, arrowText, pos[2] - font.getWidth(arrowText) - (SETTING_PADDING * 2), fontCenterY, LIGHT_FOREGROUND, true);
        }
    }

    @Override
    public void renderMode(DrawContext context, ModeElement<?> element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getOriginalHeight());
        double fontCenterY = Math.round(pos[5] - (font.fontHeight / 2.0D));

        String name = element.getProperty().getMetadata().name();
        String val = ((INameable) element.getProperty().getValue()).getName();
        String arrowText = element.isOpen() ? COLLAPSE_CHAR : EXPAND_CHAR;

        context.drawText(font, name, pos[0], (int) fontCenterY, LIGHT_FOREGROUND, true);
        context.drawText(font, val, pos[2] - font.getWidth(val + arrowText) - (SETTING_PADDING * 2), (int) fontCenterY, ACCENT, true);
        context.drawText(font, arrowText, pos[2] - font.getWidth(arrowText), (int) fontCenterY, LIGHT_FOREGROUND, true);
    }

    @Override
    public void renderMultiToggle(DrawContext context, MultiToggleElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getOriginalHeight());
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));
        String text = element.getProperty().getMetadata().name();
        String arrowText = element.isOpen() ? COLLAPSE_CHAR : EXPAND_CHAR;

        context.drawText(font, text, pos[0], fontCenterY, LIGHT_FOREGROUND, true);
        context.drawText(font, arrowText, pos[2] - font.getWidth(arrowText), fontCenterY, LIGHT_FOREGROUND, true);
    }

    @Override
    public void renderNote(DrawContext context, NoteElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));

        String text = element.getProperty().getMetadata().name();

        context.drawCenteredTextWithShadow(font, text, pos[4], fontCenterY, DARK_FOREGROUND);
    }

    @Override
    public void renderRange(DrawContext context, RangeElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));
        int valWidth = (int) element.getMinValueWidth();
        int valWidth2 = (int) element.getMaxValueWidth();
        int barSize = 3;
        int headSize = 1;

        String keyText = element.getProperty().getMetadata().name();
        String valueText = String.format("%.2f/%.2f", element.getProperty().getMinValue(), element.getProperty().getMaxValue());

        context.fill(pos[0], pos[3] - barSize, pos[2], pos[3], MODULE_BG);
        context.fill(pos[0] + valWidth, pos[3] - barSize, pos[0] + valWidth2, pos[3], ACCENT);

        // Headers
        context.fill(pos[0] + valWidth - 1, pos[3] - barSize - headSize, pos[0] + valWidth + 1, pos[3] + headSize, ACCENT);
        context.fill(pos[0] + valWidth2 - 1, pos[3] - barSize - headSize, pos[0] + valWidth2 + 1, pos[3] + headSize, ACCENT);

        context.drawText(font, keyText, pos[0] + SETTING_PADDING, fontCenterY, LIGHT_FOREGROUND, true);
        context.drawText(font, valueText, pos[2] - font.getWidth(valueText) - SETTING_PADDING, fontCenterY, LIGHT_FOREGROUND, true);
    }

    @Override
    public void renderSlider(DrawContext context, SliderElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight(), 0, 0);
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));
        int valWidth = (int) element.getValueWidth();

        int barSize = 3;
        int headSize = 1;

        String keyText = element.getProperty().getMetadata().name();
        String valueText = element.getValueText();

        // Drawing
        context.fill(pos[0], pos[3] - barSize, pos[2], pos[3], MODULE_BG);
        context.fill(pos[0], pos[3] - barSize, pos[0] + valWidth, pos[3], ACCENT);
        context.fill(pos[0] + valWidth - 1, pos[3] - barSize - headSize, pos[0] + valWidth + 1, pos[3] + headSize, ACCENT);

        context.drawText(font, keyText, pos[0] + SETTING_PADDING, fontCenterY, LIGHT_FOREGROUND, true);
        context.drawText(font, valueText, pos[2] - font.getWidth(valueText) - SETTING_PADDING, fontCenterY, LIGHT_FOREGROUND, true);
    }

    @Override
    public void renderToggle(DrawContext context, ToggleElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        int boxSize = 5;
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));
        int bgColor = element.getProperty().getValue() ? ACCENT : MODULE_BG;

        // Drawing
        context.fill(pos[2] - (boxSize * 2), pos[5] - boxSize, pos[2], pos[5] + boxSize, bgColor);
        context.drawText(font, element.getProperty().getMetadata().name(), pos[0], fontCenterY, LIGHT_FOREGROUND, true);
    }

    @Override
    public void renderOption(DrawContext context, OptionElement element, double mouseX, double mouseY) {
        int[] pos = this.calculatePositions(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        int fontCenterY = (int) Math.round(pos[5] - (font.fontHeight / 2.0D));
        int foreground = element.isValue() ? ACCENT : LIGHT_FOREGROUND;
        int barSize = 1;
        int barOff = 3;

        // Drawing
        context.fill(pos[0] + barOff, pos[1], pos[0] + barOff + barSize, pos[3], ACCENT);
        context.drawCenteredTextWithShadow(font, element.getName(), pos[4], fontCenterY, foreground);
    }

    // Shrinks and cleans the code a bit, calculates start, center, and end positions.
    private int[] calculatePositions(double x, double y, double width, double height, int xPadding, int yPadding) {
        int newX = (int) x + xPadding;
        int newY = (int) y + yPadding;
        int newEndX = (int) (x + width) - xPadding;
        int newEndY = (int) (y + height) - yPadding;
        int centerX = (int) (x + (width / 2D));
        int centerY = (int) (y + (height / 2D));

        return new int[] {newX, newY, newEndX, newEndY, centerX, centerY};
    }

    // Shrinks and cleans the code a bit, calculates start, center, and end positions.
    private int[] calculatePositions(double x, double y, double width, double height) {
        return this.calculatePositions(x, y, width, height, SETTING_PADDING, SETTING_PADDING);
    }

}

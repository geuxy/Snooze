package gg.snooze.module.impl;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.factory.PropertyFactory;
import gg.snooze.property.impl.*;
import gg.snooze.util.INameable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.lwjgl.glfw.GLFW;

import java.util.Map;

@ModuleData(
        name = "Test",
        note = "A basic test module",
        type = ModuleType.MOVEMENT,
        keyCode = GLFW.GLFW_KEY_R
)
public class TestModule extends Module {

    private final ModeProperty<TestMode> modeProperty = PropertyFactory.<TestMode>mode()
        .metadata(new PropertyMetadata("Mode", "Single selection"))
        .options(TestMode.values())
        .value(0)
        .build();

    private final MultiToggleProperty multiProperty = PropertyFactory.multiToggle()
        .metadata(new PropertyMetadata("Options", "Multi-Option selection"))
        .entries(Map.of(
            "First", false,
            "Second", true
        ))
        .build();

    private final NoteProperty noteProperty = PropertyFactory.note()
        .metadata(new PropertyMetadata("Note", "This is just a note"))
        .build();

    private final SliderProperty sliderProperty = PropertyFactory.slider()
        .metadata(new PropertyMetadata("Slider", "A slider property"))
        .value(35)
        .bounds(0, 100)
        .build();

    private final RangeProperty rangeProperty = PropertyFactory.range()
        .metadata(new PropertyMetadata("Range", "A slider property with both ends being draggable"))
        .values(30, 60)
        .bounds(0, 100)
        .build();

    private final ToggleProperty toggleProperty = PropertyFactory.toggle()
        .metadata(new PropertyMetadata("Toggle", "A property which either is ON or OFF"))
        .value(true)
        .build();

    @Getter @RequiredArgsConstructor
    enum TestMode implements INameable {
        CREATIVE("Creative"),
        MOTION("Motion");

        private final String name;
    }

}

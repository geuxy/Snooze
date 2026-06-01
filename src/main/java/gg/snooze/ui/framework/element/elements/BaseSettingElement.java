package gg.snooze.ui.framework.element.elements;

import gg.snooze.setting.Setting;
import gg.snooze.ui.framework.element.BaseElement;

public abstract class BaseSettingElement<T extends Setting> extends BaseElement {

    public final T setting;

    public BaseSettingElement(T setting) {
        this.setting = setting;
    }

}

package gg.snooze.core;

import gg.snooze.systems.event.EventBus;
import gg.snooze.core.manager.managers.ModuleManager;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter @Accessors(fluent = true)
public enum Snooze {

    INSTANCE;

    public static final String NAME = "Snooze";
    public static final String MOD_ID = "snooze";
    public static final double VERSION = 1.0;

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private EventBus eventBus;
    private ModuleManager modules;

    private boolean initialized;

    public void start() {
        if(this.initialized) {
            LOGGER.warn("Client start ignored because it has already started!");
            return;
        }

        this.initialized = true;

        this.eventBus = new EventBus((short) 64, (short) 128);
        this.modules = new ModuleManager();

        this.modules.init();
    }

    public void stop() {
        this.initialized = false;
    }

}

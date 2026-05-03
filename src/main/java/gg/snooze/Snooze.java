package gg.snooze;

import gg.snooze.event.EventBus;
import gg.snooze.manager.managers.ModuleManager;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Snooze {

    public static final Snooze INSTANCE = new Snooze();

    public static final String NAME = "Snooze";
    public static final String MOD_ID = "snooze";
    public static final double VERSION = 1.0;

    public static final Identifier IDENTIFIER = Identifier.fromNamespaceAndPath(MOD_ID, "hud");

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public final EventBus eventBus;
    public final ModuleManager modules;

    private Snooze() {
        this.eventBus = new EventBus(64, 128);
        this.modules = new ModuleManager();
    }

    private boolean initialized;

    public void start() {
        if(this.initialized) {
            LOGGER.warn("Client start ignored because it has already started!");
            return;
        }

        this.initialized = true;
        this.modules.init();
    }

}

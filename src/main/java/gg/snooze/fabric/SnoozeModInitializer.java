package gg.snooze.fabric;

import gg.snooze.Snooze;
import net.fabricmc.api.ModInitializer;

public class SnoozeModInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        Snooze.INSTANCE.start();
        FabricHooks.attachHud(Snooze.IDENTIFIER);
    }

}

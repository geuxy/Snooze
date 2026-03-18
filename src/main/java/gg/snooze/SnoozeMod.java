package gg.snooze;

import net.fabricmc.api.ModInitializer;

public class SnoozeMod implements ModInitializer {

    @Override
    public void onInitialize() {
        Snooze.INSTANCE.start();
    }

}

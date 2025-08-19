package gg.snooze;

import gg.snooze.core.Snooze;

import gg.snooze.event.EventBus;
import gg.snooze.event.impl.MovementPacketEvent;
import gg.snooze.event.listeners.MovementPacketListener;
import net.fabricmc.api.ModInitializer;

/*
 * TODO LIST:
 *  Calculate `DELTA_TIME` from `TimeUtil`.
 *  Create rotation/click handlers and managers.
 *  Create font rendering system.
 */
public class SnoozeMod implements ModInitializer, MovementPacketListener {

    @Override
    public void onInitialize() {
        Snooze.INSTANCE.start();
    }

    public SnoozeMod() {
        EventBus bus = new EventBus(2, 2);

        bus.subscribe(MovementPacketEvent.ID, this);

        long start = System.nanoTime();
        for(int i = 0; i < 10_000_000; i++) {
            bus.postUnsafe(MovementPacketEvent.ID, new MovementPacketEvent());
        }

        long end = System.nanoTime();

        System.out.println((end - start) / 1_000_000);
    }

    @Override
    public void onMovementPacket() {
    }

    public static void main(String[] args) {
        new SnoozeMod();
    }
}

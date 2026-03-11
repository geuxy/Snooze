package gg.snooze.mixin;

import gg.snooze.core.Snooze;
import gg.snooze.systems.event.events.KeyPressedEvent;
import net.minecraft.client.KeyboardHandler;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardHandler.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "keyPress")
    private void injectOnKey(long l, @KeyEvent.Action int i, KeyEvent keyEvent, CallbackInfo callback) {
        Snooze.INSTANCE.eventBus().postUnsafe(KeyPressedEvent.ID, new KeyPressedEvent(keyEvent.key(), i));
    }

}

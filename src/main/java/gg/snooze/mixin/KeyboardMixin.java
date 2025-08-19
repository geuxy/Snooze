package gg.snooze.mixin;

import gg.snooze.core.Snooze;
import gg.snooze.event.impl.KeyPressedEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(at = @At("HEAD"), method = "onKey")
    private void injectOnKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo callback) {
        Snooze.INSTANCE.eventBus().postUnsafe(KeyPressedEvent.ID, new KeyPressedEvent(key, action));
    }

}

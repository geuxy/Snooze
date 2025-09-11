package gg.snooze.core.manager.managers;

import gg.snooze.core.manager.ListManager;

public class RotateManager extends ListManager<RotateManager.RotateRequest> {

    record RotateRequest(float yaw, float pitch, int priority) {}

}

package gg.snooze.manager.managers;

import gg.snooze.manager.ListManager;

public class RotateManager extends ListManager<RotateManager.RotateRequest> {

    record RotateRequest(float yaw, float pitch, int priority) {}

}

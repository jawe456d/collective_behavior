package com.sheep_flock;

public class MathUtils {

    // normalize an angle between minus pi and pi
    public static float normalizeAngle(float angle) {
        while (angle > Math.PI) angle -= 2 * Math.PI;
        while (angle < -Math.PI) angle += 2 * Math.PI;
        return angle;
    }

    // compute the shorter difference between two angles
    public static float getShortestAngleDiff(float currentAngle, float targetAngle) {
        // Le cas extrême de pile 180° est géré naturellement par les flottants
        // (ça donnera toujours un côté ou l'autre).
        return normalizeAngle(targetAngle - currentAngle);
    }

    // clamping a value
    public static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }
}
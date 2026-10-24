package com.sheep_flock;

public class Action {

    private float desiredAngle;
    private float desiredSpeed;

    private float intensity;

    // === CONSTUCTORS ===

    public Action(float desiredAngle, float desiredSpeed, float intensity) {
        this.desiredAngle = desiredAngle;
        this.desiredSpeed = desiredSpeed;
        this.intensity = intensity;
    }

    public Action() {
        this(0f, 0f, 0f);
    }

    // === GETTERS ===

    public float desiredSpeed() {
        return desiredSpeed;
    }

    public float desiredAngle() {
        return desiredAngle;
    }

    public float intensity() {
        return intensity;
    }

    public void aggregate(Action action) {
        desiredSpeed += action.intensity() * action.desiredSpeed();
        desiredAngle += action.intensity() * action.desiredAngle();
        intensity += action.intensity();
    }

    public Action getAggregated() {
        if (intensity == 0f) {
            return new Action();
        }
        return new Action(desiredAngle / intensity, desiredSpeed / intensity, intensity);
    }

}

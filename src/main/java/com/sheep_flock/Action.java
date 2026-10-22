package com.sheep_flock;

public class Action {

    private float deltaX;
    private float deltaY;

    private float intensity;

    // === CONSTUCTORS ===

    public Action(float deltaX, float deltaY, float intensity) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.intensity = intensity;
    }

    public Action() {
        this(0f, 0f, 0f);
    }

    // === GETTERS ===

    public float deltaX() {
        return deltaX;
    }

    public float deltaY() {
        return deltaY;
    }

    public float intensity() {
        return intensity;
    }

    public void aggregate(Action action) {
        deltaX += action.intensity() * action.deltaX();
        deltaY += action.intensity() * action.deltaY();
        intensity += action.intensity();
    }

    public Action getAggregated() {
        if (intensity == 0f) {
            return new Action();
        }
        return new Action(deltaX / intensity, deltaY / intensity, intensity);
    }

}

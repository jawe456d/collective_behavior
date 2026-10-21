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

    public void agreggate(Action action) {
        deltaX += action.intensity() * action.deltaX();
        deltaX += action.intensity() * action.deltaX();
        intensity += action.intensity();
    }

    public Action getAggregated() {
        return new Action(deltaX / intensity, deltaY / intensity, intensity);
    }

}

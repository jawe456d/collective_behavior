package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;
import com.sheep_flock.geometry.*;

public abstract class Entity {

    private float absoluteX;
    private float absoluteY;
    private Shape shape;
    private Set<Behaviour> behaviours;
    private Set<Action> actions;
    private float deltaX;
    private float deltaY;

    public Entity(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape = generateMyShape();
        this.behaviours = new HashSet<Behaviour>();
        this.actions = new HashSet<Action>();
    }

    public void setPosition(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
    }

    public Shape shape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public void choose_action() {
        actions.clear();
        float totalIntensity = 0;
        float totalDeltaX = 0;
        float totalDeltaY = 0;
        for (Behaviour behaviour : behaviours){
            Action action = behaviour.evaluate(this);
            totalIntensity += action.intensity();
            totalDeltaX += action.deltaX() * action.intensity();
            totalDeltaY += action.deltaY() * action.intensity();
        }
        deltaX = totalDeltaX/totalIntensity;
        deltaY = totalDeltaY/totalIntensity;
    }

    public void act() {
        this.setPosition(absoluteX+deltaX, absoluteY+deltaY);
    }

    abstract Shape generateMyShape();
}
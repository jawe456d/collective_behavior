package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;
import com.sheep_flock.Behaviours.Behaviour;
import com.sheep_flock.geometry.*;
import com.sheep_flock.ui.Drawer;

public abstract class Entity {

    protected float absoluteX;
    protected float absoluteY;
    protected Shape shape;
    protected Set<Behaviour> behaviours;
    protected float deltaX;
    protected float deltaY;
    Behaviour currentBehaviour;
    Action currentAction;

    public Entity(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape = generateMyShape();
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
        this.behaviours = new HashSet<Behaviour>();
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
        this.shape.updateAbsolutePosition(absoluteX, absoluteY);
    }

    public void choose_action() {
        float maxIntensity = Float.NEGATIVE_INFINITY;
        for (Behaviour behaviour : behaviours){
            Action behaviourAction = behaviour.compute(this);
            if (behaviourAction.intensity() > maxIntensity){
                currentBehaviour = behaviour;
                currentAction = behaviourAction;
                maxIntensity = behaviourAction.intensity();
            }
        }   
    }

    public void act() {
        this.setPosition(absoluteX+currentAction.deltaX(), absoluteY+currentAction.deltaY());
    }

    abstract Shape generateMyShape();

    abstract void draw(Drawer drawer);

}
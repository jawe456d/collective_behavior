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

    public Entity(float absoluteX, float absoluteY) {
        this.absoluteX = absoluteX;
        this.absoluteY = absoluteY;
        this.shape = generateMyShape();
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
    }

    public void choose_action() {
        
    }

    public void act() {
        this.setPosition(absoluteX+deltaX, absoluteY+deltaY);
    }

    abstract Shape generateMyShape();

    abstract void draw(Drawer drawer);

}
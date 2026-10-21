package com.sheep_flock;

import com.sheep_flock.geometry.*;

public class Entity {

    private Position position;
    private IShape shape;

    public Entity(Position position, IShape shape) {
        this.position = position;
        this.shape = shape;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public IShape getShape() {
        return shape;
    }

    public void setShape(IShape shape) {
        this.shape = shape;
    }

    public void choose_action() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'choose_action'");
    }

    public void act() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'act'");
    }
}
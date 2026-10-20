package com.sheep_flock;

public class SpatialObject {

    private Position position;
    private IShape shape;

    public SpatialObject(Position position, IShape shape) {
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
}
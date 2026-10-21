package com.sheep_flock.geometry;

public interface IShape {

    enum ShapeKind {
        RECTANGLE, OVAL, COMPOSED
    }

    ShapeKind kind();

    float distance(IShape otherShape);

    boolean hit(IShape otherShape);

    Position getPosition();

    void updateAbsolutePosition(float parentAbsX, float parentAbsY);
}
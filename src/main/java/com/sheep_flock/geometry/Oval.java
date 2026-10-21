package com.sheep_flock.geometry;

import com.sheep_flock.IShapeVisitor;

public class Oval extends Shape {

    private float radiusX;
    private float radiusY;

    public Oval(float relativeX, float relativeY, float radiusX, float radiusY) {
        super(relativeX, relativeY);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public float distance(Shape otherShape) {
        return otherShape.accept(this);
    }

    @Override
    public <T> T accept(IShapeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public float radiusX() {
        return radiusX;
    }

    public void setRadiusX(float radiusX) {
        this.radiusX = radiusX;
    }

    public float radiusY() {
        return radiusY;
    }

    public void setRadiusY(float radiusY) {
        this.radiusY = radiusY;
    }

    @Override
    public Float visit(Oval oval) {
        return Geometry.distanceOvalOval(this, oval);
    }

    @Override
    public Float visit(Rectangle rect) {
        return Geometry.distanceOvalRect(this, rect);
    }
}
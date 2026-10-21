package com.sheep_flock.geometry;

import java.util.Set;

import com.sheep_flock.IShapeVisitor;

public class Rectangle extends Shape {

    private float width;
    private float height;

    public Rectangle(float relativeX, float relativeY, float width, float height) {
        super(relativeX, relativeY);
        this.width = width;
        this.height = height;
    }

    public float width() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float height() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Set<Shape> getSubshapes(){
        return null;
    }

    @Override
    public <T> T accept(IShapeVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Float visit(Oval oval) {
         return Geometry.distanceOvalRect(oval, this);
    }

    @Override
    public Float visit(Rectangle rect) {
        return Geometry.distanceRectRect(this, rect);
    }

    @Override
    public float distance(Shape otherShape) {
        return otherShape.accept(this);
    }
    
}
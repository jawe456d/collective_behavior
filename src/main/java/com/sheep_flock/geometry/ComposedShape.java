package com.sheep_flock.geometry;

import java.util.HashSet;
import java.util.Set;

import com.sheep_flock.IShapeVisitor;

public class ComposedShape extends Shape {

    private Set<Shape> subshapes;

    public ComposedShape(float relativeX, float relativeY) {
        super(relativeX, relativeY);
        this.subshapes = new HashSet<>();
    }

    public void addSubshape(Shape shape) {
        subshapes.add(shape);
    }

    public void removeSubshape(Shape shape) {
        subshapes.remove(shape);
    }

    public Set<Shape> subshapes() {
        return subshapes;
    }

    @Override
    public Float visit(Oval oval) {
        float minDistance = Float.POSITIVE_INFINITY;
        for (Shape subshape : subshapes){
            float distance = subshape.distance(oval);
            if (distance < minDistance){
                minDistance = distance;
            }
        }
        return minDistance;
    }

    @Override
    public Float visit(Rectangle rect) {
        float minDistance = Float.POSITIVE_INFINITY;
        for (Shape subshape : subshapes){
            float distance = subshape.distance(rect);
            if (distance < minDistance){
                minDistance = distance;
            }
        }
        return minDistance;
    }

    @Override
    public float distance(Shape otherShape){
        return otherShape.accept(this);
    }

    @Override
    public <T> T accept(IShapeVisitor<T> visitor){
        return visitor.visit(this);
    } 

    @Override
    public void updateAbsolutePosition(float parentAbsoluteX, float parentAbsoluteY) {
        super.updateAbsolutePosition(parentAbsoluteX, parentAbsoluteY);
        for (Shape subshape : subshapes){
            subshape.updateAbsolutePosition(absoluteX, absoluteY);
        }
    }

}
package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;

public class ComposedShape implements IShape {

    private Position position;
    private Set<IShape> subshapes;

    public ComposedShape(float relativeX, float relativeY, boolean isRoot) {
        this.position = new Position(relativeX, relativeY, isRoot);
        this.subshapes = new HashSet<>();
    }

    public void addSubshape(IShape shape) {
        subshapes.add(shape);
        shape.updateAbsolutePosition(this.position.getAbsoluteX(), this.position.getAbsoluteY());
    }

    public void removeSubshape(IShape shape) {
        subshapes.remove(shape);
    }

    @Override
    public ShapeKind kind() {
        return ShapeKind.COMPOSED;
    }

    @Override
    public float distance(IShape otherShape) {
        float minimumDistance = Float.POSITIVE_INFINITY;
        for (IShape subshape : subshapes) {
            float subDistance = subshape.distance(otherShape);
            if (subDistance < minimumDistance) {
                minimumDistance = subDistance;
            }
        }
        return minimumDistance;
    }

    @Override
    public boolean hit(IShape otherShape) {
        return distance(otherShape) <= 0.0f;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void updateAbsolutePosition(float parentAbsX, float parentAbsY) {
        this.position.updateAbsolute(parentAbsX, parentAbsY);
        // Immediate recursive propagation to children
        for (IShape subshape : subshapes) {
            subshape.updateAbsolutePosition(this.position.getAbsoluteX(), this.position.getAbsoluteY());
        }
    }

    public Set<IShape> getSubshapes() {
        return subshapes;
    }
}
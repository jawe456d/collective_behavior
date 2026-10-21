package com.sheep_flock.geometry;

import com.sheep_flock.IShapeVisitor;

public abstract class Shape implements IShapeVisitor<Float> {

    // relative position in the shape tree, ie the offset compared to the parent node
    private float relativeX;
    private float relativeY;

    // absolute spatial position
    protected float absoluteX;
    protected float absoluteY;
    

    public Shape(float relativeX, float relativeY) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
    }

    // --- Comportement commun partagé par toutes les formes ---

    public float absoluteX() {
        return absoluteX;
    }

    public float absoluteY() {
        return absoluteY;
    }

    public float relativeX(){
        return relativeX;
    }

    public float relativeY(){
        return relativeY;
    }

    public void setAbsolutePosition(float newAbsoluteX, float newAbsoluteY) {
        absoluteX = newAbsoluteX;
        absoluteY = newAbsoluteY;
    }

     public void setRelativePosition(float newRelativeX, float newRelativeY) {
        absoluteX = newRelativeX;
        absoluteY = newRelativeY;
    }

    public void updateAbsolutePosition(float parentAbsoluteX, float parentAbsoluteY) {
            absoluteX = parentAbsoluteX + relativeX;
            absoluteY = parentAbsoluteY + relativeY;
    }


    public boolean hit(Shape otherShape) { // Ou Shape otherShape si IShape n'existe plus
        return distance(otherShape) <= 0.0f;
    }

    @Override
    public Float visit(ComposedShape composedShape) {
        float minDistance = Float.POSITIVE_INFINITY;
        for (Shape subshape : composedShape.subshapes()){
            float distance = subshape.accept(this);
            if (distance < minDistance){
                minDistance = distance;
            }
        }
        return minDistance;
    }

    // === ABSTRACT METHODS ===

    public abstract float distance(Shape otherShape);

    public abstract <T> T accept(IShapeVisitor<T> visitor);


}
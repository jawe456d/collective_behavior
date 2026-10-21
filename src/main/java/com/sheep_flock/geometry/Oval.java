package com.sheep_flock.geometry;

public class Oval implements IShape {

    private Position position;
    private float radiusX;
    private float radiusY;

    public Oval(float relativeX, float relativeY, boolean isRoot, float radiusX, float radiusY) {
        this.position = new Position(relativeX, relativeY, isRoot);
        this.radiusX = radiusX;
        this.radiusY = radiusY;
    }

    @Override
    public ShapeKind kind() {
        return ShapeKind.OVAL;
    }

    @Override
    public float distance(IShape otherShape) {
        if (otherShape.kind() == ShapeKind.OVAL) {
            Oval other = (Oval) otherShape;
            return Geometry.distanceOvalOval(
                this.position.getAbsoluteX(), this.position.getAbsoluteY(), this.radiusX, this.radiusY,
                other.position.getAbsoluteX(), other.position.getAbsoluteY(), other.radiusX, other.radiusY
            );
        } else if (otherShape.kind() == ShapeKind.RECTANGLE) {
            Rectangle other = (Rectangle) otherShape;
            return Geometry.distanceOvalRect(
                this.position.getAbsoluteX(), this.position.getAbsoluteY(), this.radiusX, this.radiusY,
                other.getPosition().getAbsoluteX(), other.getPosition().getAbsoluteY(), other.getWidth(), other.getHeight()
            );
        } else if (otherShape.kind() == ShapeKind.COMPOSED) {
            return otherShape.distance(this);
        }
        throw new IllegalArgumentException("Unsupported shape kind");
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
    }

    public float getRadiusX() { return radiusX; }
    public void setRadiusX(float radiusX) { this.radiusX = radiusX; }
    public float getRadiusY() { return radiusY; }
    public void setRadiusY(float radiusY) { this.radiusY = radiusY; }
}
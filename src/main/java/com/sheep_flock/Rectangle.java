package com.sheep_flock;

public class Rectangle implements IShape {

    private Position position;
    private float width;
    private float height;

    public Rectangle(float relativeX, float relativeY, boolean isRoot, float width, float height) {
        this.position = new Position(relativeX, relativeY, isRoot);
        this.width = width;
        this.height = height;
    }

    @Override
    public ShapeKind kind() {
        return ShapeKind.RECTANGLE;
    }

    @Override
    public float distance(IShape otherShape) {
        if (otherShape.kind() == ShapeKind.OVAL) {
            Oval other = (Oval) otherShape;
            return Geometry.distanceOvalRect(
                other.getPosition().getAbsoluteX(), other.getPosition().getAbsoluteY(), other.getRadiusX(), other.getRadiusY(),
                this.position.getAbsoluteX(), this.position.getAbsoluteY(), this.width, this.height
            );
        } else if (otherShape.kind() == ShapeKind.RECTANGLE) {
            Rectangle other = (Rectangle) otherShape;
            return Geometry.distanceRectRect(
                this.position.getAbsoluteX(), this.position.getAbsoluteY(), this.width, this.height,
                other.position.getAbsoluteX(), other.position.getAbsoluteY(), other.width, other.height
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

    public float getWidth() { return width; }
    public void setWidth(float width) { this.width = width; }
    public float getHeight() { return height; }
    public void setHeight(float height) { this.height = height; }
}
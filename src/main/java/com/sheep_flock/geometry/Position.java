package com.sheep_flock.geometry;

public class Position {
    private float relativeX;
    private float relativeY;
    private float absoluteX;
    private float absoluteY;
    private boolean isRoot;

    public Position(float relativeX, float relativeY, boolean isRoot) {
        this.relativeX = relativeX;
        this.relativeY = relativeY;
        this.isRoot = isRoot;
        if (isRoot) {
            this.absoluteX = relativeX;
            this.absoluteY = relativeY;
        }
    }

    public void updateAbsolute(float parentAbsoluteX, float parentAbsoluteY) {
        if (isRoot) {
            this.absoluteX = this.relativeX;
            this.absoluteY = this.relativeY;
        } else {
            this.absoluteX = parentAbsoluteX + this.relativeX;
            this.absoluteY = parentAbsoluteY + this.relativeY;
        }
    }

    public float getRelativeX() {
        return relativeX;
    }

    public void setRelativeX(float relativeX) {
        this.relativeX = relativeX;
    }

    public float getRelativeY() {
        return relativeY;
    }

    public void setRelativeY(float relativeY) {
        this.relativeY = relativeY;
    }

    public float getAbsoluteX() {
        return absoluteX;
    }

    public float getAbsoluteY() {
        return absoluteY;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
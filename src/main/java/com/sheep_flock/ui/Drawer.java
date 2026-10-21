package com.sheep_flock.ui; // UI Package, separate from core

import com.sheep_flock.*;
import com.sheep_flock.geometry.*;

public class Drawer implements IShapeVisitor<Void> {

    private MyCanvas myCanvas;

    public Drawer(MyCanvas myCanvas){
        this.myCanvas = myCanvas;
    }

    // Main entry point for drawing any shape
    public void draw(Shape shape) {
        if (shape != null) {
            shape.accept(this);
        }
    }

    @Override
    public Void visit(Oval oval) {
        float x = oval.absoluteX();
        float y = oval.absoluteY();
        float rx = oval.radiusX();
        float ry = oval.radiusY();
        myCanvas.drawOval(x, y, rx, ry);
        return null;
    }

    @Override
    public Void visit(Rectangle rectangle) {
        float x = rectangle.absoluteX();
        float y = rectangle.absoluteY();
        float w = rectangle.width();
        float h = rectangle.height();
        myCanvas.drawRectangle(x, y, w, h);
        return null;
    }

    @Override
    public Void visit(ComposedShape composedShape) {
        // Recursively visit & draw all subshapes
        for (Shape subshape : composedShape.subshapes()) {
            subshape.accept(this);
        }
        return null;
    }
}
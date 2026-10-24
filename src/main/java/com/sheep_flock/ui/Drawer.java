package com.sheep_flock.ui; // UI Package, separate from core

import com.sheep_flock.*;
import com.sheep_flock.geometry.*;
import javafx.scene.paint.Color;

public class Drawer implements IShapeVisitor<Void> {

    private MyCanvas myCanvas;
    private float currentDelta;

    public Drawer(MyCanvas myCanvas) {
        this.myCanvas = myCanvas;
    }

    public void draw(Shape shape, float angle, Color color) {
        draw(shape, angle, color, false, false, Color.BLACK, 0);
    }

    // Main entry point for drawing any shape
    public void draw(Shape shape, float angle, Color color, boolean drawOutline, boolean outlineInside,
            Color outlineColor, float outlineThickness) {
        if (shape != null) {
            // 1. Save current canvas referential
            myCanvas.save();

            // 2. Turn the referential around the position of the entiry of angle radians
            // Thus, we can directly draw the shape without considering the orientation
            myCanvas.rotate(angle, shape.absoluteX(), shape.absoluteY());

            // 3. Drawing the shape
            if (drawOutline) {
                myCanvas.setColor(outlineColor);
                currentDelta = outlineInside ? 0 : outlineThickness;
                shape.accept(this);
            }
            currentDelta = drawOutline && outlineInside ? -outlineThickness : 0;
            myCanvas.setColor(color);
            shape.accept(this);

            // 4. Restore the referential of the canvas in its original position
            myCanvas.restore();
        }
    }

    @Override
    public Void visit(Oval oval) {
        float x = oval.absoluteX();
        float y = oval.absoluteY();
        float rx = oval.radiusX();
        float ry = oval.radiusY();
        myCanvas.drawOval(x, y, rx + currentDelta, ry + currentDelta);
        return null;
    }

    @Override
    public Void visit(Rectangle rectangle) {
        float x = rectangle.absoluteX();
        float y = rectangle.absoluteY();
        float w = rectangle.width();
        float h = rectangle.height();
        myCanvas.drawRectangle(x, y, w + 2 * currentDelta, h + 2 * currentDelta);
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
package com.sheep_flock;

import com.sheep_flock.geometry.*;

public interface IShapeVisitor<T> {
    T visit(Oval oval);
    T visit(Rectangle rectangle);
    T visit(ComposedShape composedShape);
}
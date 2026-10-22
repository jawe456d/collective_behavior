package com.sheep_flock;

import com.sheep_flock.Behaviours.Escaping;
import com.sheep_flock.Behaviours.Foraging;
import com.sheep_flock.geometry.*;
import com.sheep_flock.ui.Drawer;

import javafx.scene.paint.Color;

public class Sheep extends Entity {

    public Sheep(float absoluteX, float absoluteY) {
        super(absoluteX, absoluteY);
        behaviours.add(new Foraging());
        behaviours.add(new Escaping());
    }

    public Shape generateMyShape(){
        Shape mainBody = new Rectangle(0, 0, 10, 10);
        Shape front = new Oval(0, 5, 5, 5);
        Shape back = new Oval(0, -5, 5, 5);
        Shape head = new Oval(0, -10, 2, 3);
        ComposedShape myShape = new ComposedShape(0, 0);
        myShape.addSubshape(mainBody);
        myShape.addSubshape(front);
        myShape.addSubshape(back);
        myShape.addSubshape(head);
        return myShape;
    }

    @Override
    void draw(Drawer drawer) {
        drawer.draw(shape, Color.LIGHTGREY, true, true, Color.BLACK, 0.5f);
    }

    
}
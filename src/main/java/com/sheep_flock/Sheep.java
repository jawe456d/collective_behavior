package com.sheep_flock;

import com.sheep_flock.Behaviours.Escaping;
import com.sheep_flock.Behaviours.Foraging;
import com.sheep_flock.geometry.*;
import com.sheep_flock.ui.Drawer;

import javafx.scene.paint.Color;

public class Sheep extends Entity {

    private float age; // Life expectancy 10 years.
    private float weight; // We considere the ideal weight of an adult is 50 kg for an ewe and 80 for a ram. At birth : around 3.
    private boolean female; // true for an ewe, false for a ram

    public Sheep(float absoluteX, float absoluteY) {
        super(absoluteX, absoluteY);
        behaviours.add(new Foraging());
        behaviours.add(new Escaping());

        // We considere the ideal weight of an adult is 50 kg for an ewe and 80 for a ram.
        // We considere than a sheep is in overweight starting at 20% more than its ideal weight
        this.maxSpeed = 50; //computeMaxSpeed();
        this.maxAcceleration = maxSpeed/5;
        maxDecceleration = maxSpeed/5;
        visionField = 300;
        maxAngularSpeed = (float) (Math.PI/2);
    }

    private float computeMaxSpeed(){
        if (female){
            float weightPenalty = Math.min(0f, weight-80f*1.2f);
            return Math.min(8f - age*0.4f - weightPenalty*0.1f, 2f);
        } else { //male
            float weightPenalty = Math.min(0f, weight-80f*1.2f);
            return Math.min(8f - age*0.4f - weightPenalty*0.1f, 2f);
        }
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
        drawer.draw(shape, currentAngle, Color.LIGHTGREY, true, true, Color.BLACK, 0.5f);
    }

    
}
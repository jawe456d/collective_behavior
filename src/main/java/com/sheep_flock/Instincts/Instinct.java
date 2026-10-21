package com.sheep_flock.Instincts;

import com.sheep_flock.Action;
import com.sheep_flock.Entity;
import java.util.Random;

public abstract class Instinct {

    protected Random rand;

    public Instinct(){
        rand = new Random();
    }

    public abstract Action compute(Entity entity);

}

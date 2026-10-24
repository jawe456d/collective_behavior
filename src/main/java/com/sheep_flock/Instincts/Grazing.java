package com.sheep_flock.Instincts;

import com.sheep_flock.Action;
import com.sheep_flock.Entity;

public class Grazing extends Instinct {

    @Override
    public Action compute(Entity entity) {
        return new Action((float) Math.PI/2f, 100, 1);
    }
    
}

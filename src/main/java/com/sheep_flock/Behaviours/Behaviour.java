package com.sheep_flock.Behaviours;

import java.util.HashSet;
import java.util.Set;

import com.sheep_flock.Action;
import com.sheep_flock.Entity;
import com.sheep_flock.Instincts.Instinct;

public abstract class Behaviour {

    protected Set<Instinct> instincts;

    public Behaviour(){
        this.instincts = new HashSet<Instinct>();
    }

    public Action compute(Entity entity){
        Action aggregatedAction = new Action();
        for (Instinct instinct : instincts){
            aggregatedAction.aggregate(instinct.compute(entity));
        }
        return aggregatedAction.getAggregated();
    }

}

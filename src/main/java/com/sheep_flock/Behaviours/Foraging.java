package com.sheep_flock.Behaviours;

import com.sheep_flock.Instincts.Grazing;

public class Foraging extends Behaviour {

    public Foraging(){
        instincts.add(new Grazing());
    }

}

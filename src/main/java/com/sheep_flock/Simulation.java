package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;

public class Simulation {

    private Set<Entity> entities = new HashSet<Entity>();
    private boolean run = false;
    private boolean kill = false;

    //=== GETTERS & SETTERS ===//

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public Set<Entity> getEntities(){
        return entities;
    }

    public void setEntities(Set<Entity> entities){
        this.entities = entities;
    }

    public void stop(){
        run = false;
    }

    public void resume(){
        run = true;
    }

    public void kill(){
        kill = true;
    }

    //=== MAIN LOOP ===//

    public void run() {
        run = true;
        while(!kill){
            if (run){
                for (Entity entity : entities){
                    entity.choose_action();
                }
                for (Entity entity : entities){
                    entity.act();
                }
            }
        }
    }
    
}

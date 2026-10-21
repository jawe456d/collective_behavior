package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;
import com.sheep_flock.ui.Drawer;
import com.sheep_flock.ui.MyCanvas;

public class Simulation {

    private Set<Entity> entities = new HashSet<Entity>();
    private boolean run = false;
    private boolean kill = false;
    private MyCanvas myCanvas;
    private Drawer drawer;

    // === CONSTUCTORS ===

    public Simulation(MyCanvas myCanvas){
        this.myCanvas = myCanvas;
        this.drawer = new Drawer(myCanvas);
    }

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
                myCanvas.clean();
                for (Entity entity : entities){
                    entity.act();
                    entity.draw(drawer);
                }
                myCanvas.show();
            }
        }
    }
    
}

package com.sheep_flock;

import java.util.HashSet;
import java.util.Set;

import javafx.animation.AnimationTimer;
import com.sheep_flock.ui.Drawer;
import com.sheep_flock.ui.MyCanvas;

public class Simulation {

    private Set<Entity> entities = new HashSet<>();
    private boolean running;
    private boolean killed;
    private final MyCanvas myCanvas;
    private final Drawer drawer;
    private AnimationTimer animationTimer;

    // === CONSTUCTORS ===

    public Simulation(MyCanvas myCanvas) {
        this.myCanvas = myCanvas;
        this.drawer = new Drawer(myCanvas);
    }

    // === GETTERS & SETTERS ===

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
    }

    public void stop() {
        running = false;
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    public void resume() {
        if (!killed) {
            running = true;
            if (animationTimer != null) {
                animationTimer.start();
            }
        }
    }

    public void kill() {
        killed = true;
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    // === MAIN LOOP ===

    public void run() {
        if (killed) {
            return;
        }
        if (animationTimer == null) {
            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (killed) {
                        animationTimer.stop();
                        return;
                    }
                    if (running) {
                        updateFrame();
                    }
                }
            };
        }
        resume();
    }

    private void updateFrame() {
        for (Entity entity : entities) {
            entity.choose_action();
        }
        myCanvas.clean();
        for (Entity entity : entities) {
            entity.act();
            entity.draw(drawer);
        }
    }

}

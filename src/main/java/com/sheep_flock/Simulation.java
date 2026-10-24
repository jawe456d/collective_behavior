package com.sheep_flock;

import java.util.HashSet;
import java.util.Locale;
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
    private long lastUpdateTime;
    private Sheep dolly;

    // === CONSTUCTORS ===

    public Simulation(MyCanvas myCanvas) {
        this.myCanvas = myCanvas;
        this.drawer = new Drawer(myCanvas);
    }

    // === GETTERS & SETTERS ===

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void setDolly(Sheep dolly) {
        this.dolly = dolly;
    }

    public String getStatusAsString() {
        if (dolly == null) {
            return "";
        }
        return String.format(Locale.ROOT,
                "Dolly position X : %.2f px%nDolly position Y : %.2f px%nDolly speed : %.2f px/s",
                dolly.getAbsoluteX(), dolly.getAbsoluteY(), dolly.getCurrentSpeed());
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
        if (dolly == entity) {
            dolly = null;
        }
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void setEntities(Set<Entity> entities) {
        this.entities = entities;
        if (dolly != null && !entities.contains(dolly)) {
            dolly = null;
        }
    }

    public void stop() {
        running = false;
        if (animationTimer != null) {
            animationTimer.stop();
        }
    }

    public void resume() {
        if (!killed) {
            if (!running) {
                lastUpdateTime = 0;
            }
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
                        float deltaT = lastUpdateTime == 0
                                ? 0
                                : (now - lastUpdateTime) / 1_000_000_000f;
                        lastUpdateTime = now;
                        updateFrame(deltaT);
                    }
                }
            };
        }
        resume();
    }

    private void updateFrame(float deltaT) {
        for (Entity entity : entities) {
            entity.choose_action();
        }
        myCanvas.clean();
        for (Entity entity : entities) {
            entity.act(deltaT);
            entity.draw(drawer);
        }
        myCanvas.drawStatus(getStatusAsString());
    }

}

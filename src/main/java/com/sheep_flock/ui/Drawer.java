package com.sheep_flock.ui;

import com.sheep_flock.Entity;
import com.sheep_flock.geometry.IShape;

public class Drawer implements IDrawer {

    @Override
    public void draw(Entity entity){
        IShape shape = entity.getShape();
        
    }

}

package com.sheep_flock;

import com.sheep_flock.ui.MyCanvas;

import javafx.stage.Stage;

public class Main {

    public static void main(String[] args) {
        Stage stage = new Stage();
        MyCanvas myCanvas = new MyCanvas(stage);
        Simulation simulation = new Simulation(myCanvas);
        Sheep dolly = new Sheep(0 , 0);
        simulation.addEntity(dolly);
        simulation.run();
    }

}

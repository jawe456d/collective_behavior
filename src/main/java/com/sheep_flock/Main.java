package com.sheep_flock;

import com.sheep_flock.ui.MyCanvas;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MyCanvas myCanvas = new MyCanvas(stage, 800, 600);
        Simulation simulation = new Simulation(myCanvas);
        Sheep dolly = new Sheep(100, 100);
        simulation.addEntity(dolly);
        stage.setOnCloseRequest(event -> simulation.kill());
        simulation.run();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

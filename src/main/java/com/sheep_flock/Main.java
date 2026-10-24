package com.sheep_flock;

import java.io.InputStream;

import com.sheep_flock.ui.MyCanvas;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    private int canvasWidth = 800;
    private int canvasHeight = 600;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Sheep behaviour simulation");
        try (InputStream logo = Main.class.getResourceAsStream("/logo.png")) {
            if (logo == null) {
                throw new IllegalStateException("Window logo resource not found: /logo.png");
            }
            stage.getIcons().add(new Image(logo));
        } catch (java.io.IOException exception) {
            throw new IllegalStateException("Unable to load window logo: /logo.png", exception);
        }

        MyCanvas myCanvas = new MyCanvas(stage, canvasWidth, canvasHeight);
        Simulation simulation = new Simulation(myCanvas);
        Sheep dolly = new Sheep(canvasWidth/2, canvasHeight/2);
        simulation.addEntity(dolly);
        simulation.setDolly(dolly);
        stage.setOnCloseRequest(event -> simulation.kill());
        simulation.run();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

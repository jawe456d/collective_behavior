package com.sheep_flock.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyCanvas {

    private GraphicsContext gc;
    private Stage stage;
    private Canvas canvas;

    public MyCanvas(Stage stage) {
        this.stage = stage;
        canvas = new Canvas(400, 300);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
    }

    // Show the canvas in its current stage
    public void show(){
        stage.show();
    }

    // Clean but without modifying the current printing
    public void clean(){
        gc = canvas.getGraphicsContext2D();
    }

    public void drawRectangle(float x, float y, float w, float h) {
        gc.setFill(Color.BLUE);
        gc.setStroke(Color.BLACK);
        gc.fillRect(20, 20, 150, 100); // Solid blue rectangle
        gc.strokeRect(20, 20, 150, 100); // Black outline
    }

    public void drawOval(float x, float y, float rx, float ry) {
        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.fillOval(200, 20, 150, 100); // Solid red oval
        gc.strokeOval(200, 20, 150, 100); // Black outline
    }

}

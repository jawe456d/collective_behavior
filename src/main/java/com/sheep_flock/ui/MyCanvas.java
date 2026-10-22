package com.sheep_flock.ui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyCanvas {

    private final GraphicsContext gc;
    private final Stage stage;
    private final Canvas canvas;

    private final int width;
    private final int height;
    private Color color = Color.BLACK;

    public MyCanvas(Stage stage, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Canvas dimensions must be positive");
        }
        this.stage = stage;
        this.width = width;
        this.height = height;
        canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();
        StackPane root = new StackPane(canvas);
        stage.setScene(new Scene(root, width, height));
        stage.show();
    }

    // Show the canvas in its current stage
    public void show(){
        stage.show();
    }

    // Clean but without modifying the current printing
    public void clean(){
        gc.clearRect(0, 0, width, height);
    }

    public void drawRectangle(float x, float y, float w, float h) {
        gc.setFill(color);
        gc.fillRect(x - w / 2, y - h / 2, w, h);
    }

    public void drawOval(float x, float y, float rx, float ry) {
        gc.setFill(color);
        gc.fillOval(x - rx, y - ry, 2 * rx, 2 * ry);
    }

    public void setColor(Color color) {
        this.color = color;
    }

}

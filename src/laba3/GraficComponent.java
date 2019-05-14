/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author alexey
 */
public class GraficComponent {
    Canvas canvas;
    GraphicsContext graphicsContext;
    VBox vBox;
    double width;
    double height;
    
    public GraficComponent(double width, double height){
        this.width = width;
        this.height = height;
        canvas = new Canvas(width, height);
        vBox = new VBox(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);
        graphicsContext.strokeLine(width/2, 0, width/2, height);
        graphicsContext.strokeLine(0, height/2, width, height/2);
        graphicsContext.strokeLine(width/2 - 5, 5, width/2, 0);
        graphicsContext.strokeLine(width/2 + 5, 5, width/2, 0);
        graphicsContext.strokeLine(width - 5, height/2 - 5, width, height/2);
        graphicsContext.strokeLine(width - 5, height/2 + 5, width, height/2);
        graphicsContext.strokeText("X", width - 13, height/2 + 30);
        graphicsContext.strokeText("Y", width/2 - 18, 14);
        writeNumbers();
    }
    
    public VBox getVBox(){
        return vBox;
    }
    
    private void writeNumbers(){
        int scale = 25;
        double x = width/2;
        double y = height/2;
        while(x < width){
            graphicsContext.strokeLine(x, height/2 - 5, x, height/2 + 5);
            graphicsContext.strokeText(Integer.toString((int)(x - width/2)/scale),
                    x + 5, height/2 + 15);
            graphicsContext.strokeLine(width - x, height/2 - 5,width - x, height/2 + 5);
            if((width/2 - x)/scale != 0){
                graphicsContext.strokeText(Integer.toString((int)(width/2 - x)/scale),
                    width - x, height/2 + 15);
            }
            x += scale;
        }
        while(y < height){
            graphicsContext.strokeLine(width/2 - 5, y, width/2 + 5, y);
            graphicsContext.strokeLine(width/2 - 5, height - y, width/2 + 5, height - y);
            if((y - height/2)/scale != 0){
                graphicsContext.strokeText(Integer.toString((int)(y - height/2)/scale),
                    width/2 - 15, height - y + 5);
                graphicsContext.strokeText(Integer.toString((int)(height/2 - y)/scale),
                    width/2 - 20, y + 5);
            }
            y += scale;
        }
    }
    
    public void paintGrafic(List<Function> list){
        
    }
}

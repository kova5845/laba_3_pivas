/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author alexey
 */
public class MainDialog extends Application{
    Controller controller = new Controller(this);
    MainDialog mainDialog;
    ComboBox<String> funcComboBox;
    Button startButton;
    Button stopButton;
    VBox vBox;
    HBox hBox;
    HBox buttonHBox;
    ObservableList<Function> data = FXCollections.observableArrayList();
    TableView<Function> table;
    TableColumn xColumn;
    TableColumn yColumn;
    GraficComponent graficComponent;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        funcComboBox = new ComboBox<>();
        funcComboBox.getItems().addAll("f(x)=x+1", "f(x)=");
        funcComboBox.setValue(funcComboBox.getItems().get(0));
        startButton = new Button("start");
        stopButton = new Button("stop");
        graficComponent = new GraficComponent(300, 450);
        table = new TableView<>();
        table.setMinWidth(300);
        xColumn = new TableColumn<>("x");
        xColumn.setMinWidth(140);
        xColumn.setCellValueFactory(new PropertyValueFactory("x"));
        yColumn = new TableColumn<>("y");
        yColumn.setMinWidth(140);
        yColumn.setCellValueFactory(new PropertyValueFactory("y"));
        table.getColumns().addAll(xColumn, yColumn);
        table.setItems(data);
        vBox = new VBox();
        hBox = new HBox();
        buttonHBox = new HBox(startButton, stopButton);
        vBox.getChildren().addAll(funcComboBox, table, buttonHBox);
        hBox.getChildren().addAll(vBox, graficComponent.getVBox());
        
        Exchanger<List<Function>> exchanger = new Exchanger<>();
        PaintThread paintThread = new PaintThread(exchanger);
        Thread thread1 = new Thread(paintThread);
        CountThread countThread = new CountThread(exchanger);
        Thread thread2 = new Thread(countThread);
        startButton.setOnAction((ActionEvent event) -> {
            thread2.start();
            thread1.start();
        });
        stopButton.setOnAction((ActionEvent event) -> {
            thread1.stop();
            thread2.stop();
        });
        
        Scene scene = new Scene(hBox);
        primaryStage.setTitle("Лабараторная работа №3");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }
    
    class PaintThread implements Runnable {
        Exchanger<List<Function>> exchanger;

        public PaintThread(Exchanger<List<Function>> ex){
            this.exchanger=ex;
        }
        @Override
        public void run(){
            Runnable updater = new Runnable() {
                @Override
                public void run() {
                    try {
                        data.addAll(exchanger.exchange(null));
                        graficComponent.printGrafic(data);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
            while(true){
                Platform.runLater(updater);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    class CountThread implements Runnable{
        Exchanger<List<Function>> exchanger;

        public CountThread(Exchanger<List<Function>> ex){
            this.exchanger=ex;
        }
        @Override
        public void run(){
            while(true){
                controller.firstFunction(-150, 150, 25);
                try {
                    exchanger.exchange(controller.getData());
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MainDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba3;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    MainDialog mainDialog;
    ComboBox funcComboBox;
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
        funcComboBox = new ComboBox();
        startButton = new Button("start");
        stopButton = new Button("stop");
        graficComponent = new GraficComponent(300, 450);
        table = new TableView<>();
        xColumn = new TableColumn<>("x");
        xColumn.setCellValueFactory(new PropertyValueFactory("x"));
        yColumn = new TableColumn<>("y");
        yColumn.setCellValueFactory(new PropertyValueFactory("y"));
        table.getColumns().addAll(xColumn, yColumn);
        table.setItems(data);
        vBox = new VBox();
        hBox = new HBox();
        buttonHBox = new HBox(startButton, stopButton);
        vBox.getChildren().addAll(funcComboBox, table, buttonHBox);
        hBox.getChildren().addAll(vBox, graficComponent.getVBox());
        Scene scene = new Scene(hBox);
        primaryStage.setTitle("Лабараторная работа №3");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
    }
}

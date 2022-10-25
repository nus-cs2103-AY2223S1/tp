package nus.climods;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nus.climods.ui.module.ModuleStub;
import nus.climods.ui.module.ViewLesson;

public class MainApp2 extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        ViewLesson vl = new ViewLesson(new ModuleStub());
        StackPane root = new StackPane();
        root.getChildren().add(vl.getRoot());
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}

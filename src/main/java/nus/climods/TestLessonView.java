package nus.climods;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nus.climods.ui.module.ModuleStub;
import nus.climods.ui.module.ViewLesson;

/**
 * Dummy class used to test Lesson View
 */
public class TestLessonView extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        ViewLesson vl = new ViewLesson(new ModuleStub());
        StackPane root = new StackPane();
        Node n = vl.getRoot();
        root.getChildren().add(n);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}

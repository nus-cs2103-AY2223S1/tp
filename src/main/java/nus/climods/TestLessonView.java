package nus.climods;

import java.util.List;
import java.util.Optional;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;
import org.openapitools.client.model.ModuleInformation;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import nus.climods.model.module.Module;
import nus.climods.ui.module.ViewLesson;

/**
 * Dummy class used to test Lesson View
 */
public class TestLessonView extends Application {
    private static final String TEST_ACADEMIC_YEAR = "2022-2023";
    private final List<ModuleInformation> moduleInformationList =
            ModulesApi.getInstance().acadYearModuleInfoJsonGet(TEST_ACADEMIC_YEAR);
    private final Module testModuleCS1101S = new Module(getModuleInformation("CS1101S"), TEST_ACADEMIC_YEAR);
    private final Module testModuleCS2103 = new Module(getModuleInformation("CS2103"), TEST_ACADEMIC_YEAR);

    public TestLessonView() throws ApiException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    private ModuleInformation getModuleInformation(String moduleCode) {
        Optional<ModuleInformation> moduleInfo =
                moduleInformationList.stream().filter(info -> info.getModuleCode().equals(moduleCode)).findFirst();
        assert moduleInfo.isPresent();

        return moduleInfo.get();
    }

    @Override
    public void start(Stage primaryStage) throws ApiException {
        primaryStage.setTitle("Hello World!");
        testModuleCS1101S.requestFocus();
        ViewLesson vl = new ViewLesson(testModuleCS1101S);
        StackPane root = new StackPane();
        Node n = vl.getRoot();
        root.getChildren().add(n);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}

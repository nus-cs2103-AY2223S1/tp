package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui extends Observer {

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage);

}

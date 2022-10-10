package seedu.address.ui;

import javafx.stage.Stage;

/**
 * API of UI component
 */
public interface Ui {

    /** Starts the UI (and the App) without a launch message. */
    void start(Stage primaryStage);

    /** Starts the UI (and the App).  */
    void start(Stage primaryStage, String launchMessage);

}

package seedu.foodrem.ui;

import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.foodrem.MainApp;
import seedu.foodrem.commons.core.LogsCenter;
import seedu.foodrem.commons.util.StringUtil;
import seedu.foodrem.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {
    private static final Logger logger = LogsCenter.getLogger(UiManager.class);

    private final Logic logic;
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters.
     * This method only returns after the user has closed the alert dialog.
     */
    private static void showAlertDialogAndWait(
            Stage owner, AlertType type, String title, String headerText, String contentText) {
        final Alert alert = new Alert(type);
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setId("alertDialogPane");
        alert.showAndWait();
    }

    void showAlertDialogAndWait(Alert.AlertType type, String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), type, title, headerText, contentText);
    }

    @Override
    public void start(Stage primaryStage, String message) {
        logger.info("Starting UI...");

        // Set the application icon.
        final Image icon = new Image(MainApp.class.getResourceAsStream("/images/foodrem.png"));
        primaryStage.getIcons().add(icon);

        try {
            mainWindow = new MainWindow(primaryStage, logic, message);
            mainWindow.show(); // This should be called before creating other UI parts
            mainWindow.fillInnerParts();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown("Fatal error during initializing", e);
        }
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     */
    private void showFatalErrorDialogAndShutdown(String title, Throwable e) {
        logger.severe(title + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait(Alert.AlertType.ERROR, title, e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }
}

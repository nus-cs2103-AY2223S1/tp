package seedu.masslinkers.ui;

import java.util.Objects;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import seedu.masslinkers.MainApp;
import seedu.masslinkers.commons.core.LogsCenter;
import seedu.masslinkers.commons.util.StringUtil;
import seedu.masslinkers.logic.Logic;

/**
 * The manager of the UI component.
 */
public class UiManager implements Ui {

    public static final String ALERT_DIALOG_PANE_FIELD_ID = "alertDialogPane";

    private static final Logger logger = LogsCenter.getLogger(UiManager.class);
    private static final String ICON_APPLICATION = "/images/address_book_32.png";

    private final Logic logic;
    private MainWindow mainWindow;

    /**
     * Creates a {@code UiManager} with the given {@code Logic}.
     */
    public UiManager(Logic logic) {
        this.logic = logic;
    }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Starting UI...");

        //Set the application icon.
        primaryStage.getIcons().add(getImage());

        try {
            mainWindow = new MainWindow(primaryStage, logic);
            mainWindow.show(); //This should be called before creating other UI parts
            mainWindow.fillInnerParts();
        } catch (Throwable e) {
            logger.severe(StringUtil.getDetails(e));
            showFatalErrorDialogAndShutdown(e);
        }
    }

    private Image getImage() {
        return new Image(Objects.requireNonNull(MainApp.class.getResourceAsStream(UiManager.ICON_APPLICATION)));
    }

    void showAlertDialogAndWait(String title, String headerText, String contentText) {
        showAlertDialogAndWait(mainWindow.getPrimaryStage(), title, headerText, contentText);
    }

    /**
     * Shows an alert dialog on {@code owner} with the given parameters.
     * This method only returns after the user has closed the alert dialog.
     */
    private static void showAlertDialogAndWait(Stage owner, String title, String headerText,
                                               String contentText) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.getDialogPane().getStylesheets().add("view/DarkTheme.css");
        alert.initOwner(owner);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.getDialogPane().setId(ALERT_DIALOG_PANE_FIELD_ID);
        alert.showAndWait();
    }

    /**
     * Shows an error alert dialog with {@code title} and error message, {@code e},
     * and exits the application after the user has closed the alert dialog.
     */
    private void showFatalErrorDialogAndShutdown(Throwable e) {
        logger.severe("Fatal error during initializing" + " " + e.getMessage() + StringUtil.getDetails(e));
        showAlertDialogAndWait("Fatal error during initializing", e.getMessage(), e.toString());
        Platform.exit();
        System.exit(1);
    }

}

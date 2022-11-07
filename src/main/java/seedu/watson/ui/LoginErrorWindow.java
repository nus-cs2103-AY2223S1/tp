package seedu.watson.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.watson.commons.core.LogsCenter;

/**
 * Controller for a login error page
 */
public class LoginErrorWindow extends UiPart<Stage> {

    public static final String ERROR_MESSAGE = "Error: Invalid credentials!";

    private static final Logger logger = LogsCenter.getLogger(
        LoginErrorWindow.class
    );
    private static final String FXML = "LoginErrorWindow.fxml";

    @FXML
    private Label errorMessage;

    /**
     * Creates a new LoginErrorWindow.
     *
     * @param root Stage to use as the root of the LoginErrorWindow.
     */
    public LoginErrorWindow(Stage root) {
        super(FXML, root);
        errorMessage.setText(ERROR_MESSAGE);
    }

    /**
     * Creates a new LoginErrorWindow.
     */
    public LoginErrorWindow() {
        this(new Stage());
    }

    /**
     * Shows the help window.
     */
    public void show() {
        logger.fine("Error: Invalid credentials!");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
}

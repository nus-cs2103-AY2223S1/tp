package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

/**
 * Controller for FinBook lock dialog
 */
public class LockWindow extends UiPart<Stage> {
    private static final Logger logger = LogsCenter.getLogger(LockWindow.class);
    private static final String FXML = "LockWindow.fxml";

    private MainWindow mainWindow;

    @FXML
    private TextField password;

    /**
     * Creates a new LockWindow.
     *
     * @param root       Stage to use as the root of the LockWindow.
     * @param mainWindow Main window of FinBook.
     */
    public LockWindow(Stage root, MainWindow mainWindow) {
        super(FXML, root);
        this.mainWindow = mainWindow;
    }

    /**
     * Shows the FinBook lock dialog.
     *
     * @throws IllegalStateException <ul>
     *                               <li>
     *                               if this method is called on a thread other than the JavaFX Application Thread.
     *                               </li>
     *                               <li>
     *                               if this method is called during animation or layout processing.
     *                               </li>
     *                               <li>
     *                               if this method is called on the primary stage.
     *                               </li>
     *                               <li>
     *                               if {@code dialogStage} is already showing.
     *                               </li>
     *                               </ul>
     */
    public void show() {
        logger.fine("Showing FinBook lock dialog.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the FinBook lock dialog is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the FinBook lock dialog.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the FinBook lock dialog.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Attempts to unlock FinBook.
     */
    @FXML
    private void unlock() {
        logger.info("FinBook successfully unlocked.");
        hide();
        mainWindow.getPrimaryStage().show();
    }
}

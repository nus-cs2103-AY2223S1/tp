package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for a help page
 */
public class DisplayUserWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(DisplayUserWindow.class);
    private static final String FXML = "DisplayUserWindow.fxml";
    private PersonListPanel personListPanel;

    @FXML
    private StackPane personListPanelPlaceholder;

    /**
     * Creates a new display user window.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public DisplayUserWindow(Stage root, Logic logic) {
        super(FXML, root);
        personListPanel = new PersonListPanel(logic.getFilteredPersonList());
        personListPanelPlaceholder.getChildren().add(personListPanel.getRoot());
    }

    /**
     * Creates a new display user.
     */
    public DisplayUserWindow(Logic logic) {
        this(new Stage(), logic);

    }

    /**
     * Shows the display user window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing user list in the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the display user window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the display user window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the display user window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}

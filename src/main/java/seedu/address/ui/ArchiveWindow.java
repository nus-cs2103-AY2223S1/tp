package seedu.address.ui;

import java.awt.Label;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;

/**
 * Controller for archive page.
 */
public class ArchiveWindow extends UiPart<Stage> {

    private static final String FXML = "ArchiveWindow.fxml";
    private static final Logger logger = LogsCenter.getLogger(ArchiveWindow.class);

    private Logic logic;

    // Independent Ui part residing in this Ui container
    private TaskListPanel archivedTaskListPanel;

    @FXML
    private Label archiveMessage;

    @FXML
    private StackPane archiveListPanelPlaceholder;

    /**
     * Creates a new archived window.
     */
    public ArchiveWindow(Logic logic) {
        this(new Stage());
        this.logic = logic;
    }

    /**
     * Creates a new archived window.
     *
     * @param root Stage to use as the root of the ArchiveWindow.
     */
    public ArchiveWindow(Stage root) {
        super(FXML, root);
    }


    /**
     * Shows the archive window.
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
        logger.fine("Showing archived tasks.");
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

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        archivedTaskListPanel = new TaskListPanel(logic.getObservableArchivedTaskList());
        archiveListPanelPlaceholder.getChildren().add(archivedTaskListPanel.getRoot());
    }
}

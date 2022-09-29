package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Controller for a help page
 */
public class ReminderWindow extends UiPart<Stage> {

    public static final String REMINDER_MESSAGE = "This is from the reminder component!";

    private static final Logger logger = LogsCenter.getLogger(ReminderWindow.class);
    private static final String FXML = "ReminderWindow.fxml";
    private ObservableList<Person> personObservableList;

    @FXML
    private Label reminderMessage;

    /**
     * Creates a new HelpWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public ReminderWindow(Stage root) {
        super(FXML, root);
        reminderMessage.setText(REMINDER_MESSAGE);
    }

    /**
     * Creates a new HelpWindow.
     */
    public ReminderWindow(ObservableList<Person> personObservableList) {
        this(new Stage());
        this.personObservableList = personObservableList;
    }

    public String getList() {
        return personObservableList.toString();
    }

    /**
     * Shows the help window.
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
        logger.fine("Showing help page about the application.");
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

package seedu.address.ui;

import java.util.Set;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.module.Lesson;

/**
 * Window to show timetable requested.
 */
public class TimetableWindow extends UiPart<Stage> {
    private static final String FXML = "TimetableWindow.fxml";

    private final Logger logger = LogsCenter.getLogger(TimetableWindow.class);

    @FXML
    private Label timetableMessage;

    /**
     * Creates a new TimetableWindow.
     *
     * @param root Stage to use as the root of the TimetableWindow.
     */
    public TimetableWindow(Stage root) {
        super(FXML, root);
        System.out.println("constructor called");
        timetableMessage.setText("yet to intialise!");
    }

    /**
     * Creates a new TimetableWindow.
     */
    public TimetableWindow() {
        this(new Stage());
    }

    /**
     * Shows the timetable window.
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
        logger.fine("Showing timetable page.");
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
     * Hides the timetable window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the timetable window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Sets the text to show to the timetable
     *
     * @param lessons set of lessons to show - from user or contact
     */
    public void setTimetableMessage(Set<Lesson> lessons) {
        this.timetableMessage.setText(lessons.toString());
    }
}

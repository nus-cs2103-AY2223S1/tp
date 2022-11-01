package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;

/**
 * Controller for a meetings page
 */
public class MeetingsWindow extends UiPart<Stage> {
    public static final String MEETINGS_MESSAGE = "These are the meetings you have for the next 7 days.";
    private static final Logger logger = LogsCenter.getLogger(MeetingsWindow.class);
    private static final String FXML = "MeetingsWindow.fxml";
    private MeetingListPanel meetingListPanel;
    private final Logic logic;

    @FXML
    private StackPane meetingListPanelPlaceholder;

    @FXML
    private Label meetingsMessage;

    /**
     * Creates a new MeetingsWindow.
     *
     * @param root Stage to use as the root of the MeetingsWindow.
     */
    public MeetingsWindow(Stage root, Logic logic) {
        super(FXML, root);
        meetingsMessage.setText(MEETINGS_MESSAGE);
        this.logic = logic;
    }

    /**
     * Creates a new MeetingsWindow.
     */
    public MeetingsWindow(Logic logic) {
        this(new Stage(), logic);
    }

    /**
     * Shows the meetings window.
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
        logger.fine("Showing meetings page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the meetings window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the meetings window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the meetings window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Generates a list of meetings with all contacts in FABook and creates a meetingListPanel
     */
    protected void getMeetings() {
        ObservableList<Person> personObservableList = logic.getFilteredPersonList();
        ObservableList<Person> filteredList =
            personObservableList.filtered(person ->
                person.getMeetingTimes().stream().anyMatch(meetingTime -> isWithinOneWeek(meetingTime.getDate())));
        meetingListPanel = new MeetingListPanel(filteredList);
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());
    }

    /**
     * Checks if a given local date time is within one week from now
     * @param meetingTime
     * @return Boolean. True if within one week
     */
    private boolean isWithinOneWeek(LocalDateTime meetingTime) {
        LocalDateTime now = LocalDateTime.now();
        return meetingTime.isAfter(now) && meetingTime.isBefore(now.plusWeeks(1));
    }
}

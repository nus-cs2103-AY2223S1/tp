package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;

public class MeetingsWindow extends UiPart<Stage> {
    public static final String MEETINGS_MESSAGE = "These are the meetings you have";
    private static final Logger logger = LogsCenter.getLogger(HelpWindow.class);
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

    void getMeetings() {
        ObservableList<Person> personObservableList = logic.getFilteredPersonList();
        ObservableList<Person> filteredList =
        personObservableList.filtered(person ->
                person.getMeetingTimes().stream().anyMatch(meetingTime -> isWithinOneWeek(meetingTime.getDate())));

//        List<Person> filteredMeetingList = new ArrayList<Person>();
//
//        for (Person person: filteredList) {
//            filteredMeetingList.add(person)
//        }

        meetingListPanel = new MeetingListPanel(filteredList);
        meetingListPanelPlaceholder.getChildren().add(meetingListPanel.getRoot());
    }

    private boolean isWithinOneWeek(LocalDateTime meetingTime) {
        LocalDateTime now = LocalDateTime.now();
        return meetingTime.isAfter(now) && meetingTime.isBefore(now.plusWeeks(1));
    }
}

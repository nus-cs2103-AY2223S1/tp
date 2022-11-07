package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.MeetingTime;
import seedu.address.model.person.Person;

/**
 * An UI component that displays summarised information of a {@code Person}.
 */
public class MeetingCard extends UiPart<Region> {
    private static final String FXML = "MeetingListCard.fxml";
    public final Person person;
    private MainDisplay mainDisplayListener;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private FlowPane meetingTimes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public MeetingCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().getFullName());
        person.getMeetingTimes().stream()
                .filter(meetingTime -> isWithinOneWeek(meetingTime.getDate()))
                .sorted(Comparator.comparing(MeetingTime::getDate))
                .forEach(meetingTime -> meetingTimes.getChildren().add(new Label(meetingTime.displayValue)));
    }

    public void addMainDisplayListener(MainDisplay mainDisplay) {
        mainDisplayListener = mainDisplay;
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

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MeetingCard)) {
            return false;
        }

        // state check
        MeetingCard card = (MeetingCard) other;
        return person.equals(card.person);
    }
}

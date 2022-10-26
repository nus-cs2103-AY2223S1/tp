package seedu.address.ui;

import static seedu.address.logic.parser.ParserUtil.DATE_FORMAT_PATTERN;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;

/**
 * An UI component that displays reminder information of a {@code Person}.
 */
public class ReminderCard extends UiPart<Region> {

    private static final String FXML = "ReminderListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label birthday;
    @FXML
    private Label reminderTask;
    @FXML
    private Label reminderDate;
    /**
     * Creates a {@code ReminderCard} with the given {@code Person} and index to display.
     */
    public ReminderCard(Person person, Reminder reminder, int displayedIndex) {
        super(FXML);
        assert person != null : "Something went wrong in UI ReminderCard";
        this.person = person;

        assert person.getName() != null : "Something went wrong in UI ReminderCard name";
        assert reminder.task != null : "Something went wrong in UI ReminderCard task";
        assert reminder.date != null : "Something went wrong in UI ReminderCard date";
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        reminderTask.setText("\uD83D\uDDD2\t" + reminder.task);
        reminderDate.setText("\uD83D\uDCC5\t" + reminder.date
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        ReminderCard card = (ReminderCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

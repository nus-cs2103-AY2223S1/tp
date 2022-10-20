package seedu.address.ui;

import java.time.LocalDateTime;
import java.util.Comparator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of the {@code Person}
 * selected by the `show` command.
 */
public class TargetPersonCard extends UiPart<Region> {

    private static final String FXML = "TargetPersonCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    private static final String NO_REMARKS_VALUE = "No remarks";

    private static final String ITALIC_FONT_STYLE = "-fx-font-style: italic";
    private static final String NORMAL_FONT_STYLE = "-fx-font-style: normal";

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private ListView<TempReminder> reminderView;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TargetPersonCard(Person person) {
        super(FXML);
        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        if (person.getRemark().value == "") {
            remark.setText(NO_REMARKS_VALUE);
            remark.setStyle(ITALIC_FONT_STYLE);
        } else {
            remark.setText(person.getRemark().value);
            remark.setStyle(NORMAL_FONT_STYLE);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        ObservableList<TempReminder> remindersPlaceholder = FXCollections.observableArrayList(
            new TempReminder("Reminder 1", LocalDateTime.now()),
            new TempReminder("Reminder 2", LocalDateTime.now().plusDays(1)),
            new TempReminder("Reminder 3 which is a very long reminder just to test"
                    + " whether the text UI component will warp.", LocalDateTime.now().plusDays(4)),
            new TempReminder("Reminder 4", LocalDateTime.now().plusDays(3)),
            new TempReminder("Reminder 5", LocalDateTime.now().plusDays(2))
        );
        reminderView.setItems(remindersPlaceholder);
        reminderView.setCellFactory(listView -> new TempReminderListViewCell());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TargetPersonCard)) {
            return false;
        }

        // state check
        TargetPersonCard card = (TargetPersonCard) other;
        return person.equals(card.person);
    }
}

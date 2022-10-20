package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.TimeSlot;

/**
 * A UI component that displays information of a {@code TimeSlot}.
 */
public class TimeSlotCard extends UiPart<Region> {

    private static final String FXML = "TimeSlotCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox timeCardPane;
    @FXML
    private Label sessionTime;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label lessonPlan;
    @FXML
    private Label homework;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code TimeSlotCard} with the given {@code TimeSlot}.
     */
    public TimeSlotCard(TimeSlot timeSlot) {
        super(FXML);
        sessionTime.setText(timeSlot.getSession().time.toString());
        this.person = timeSlot.getPerson();
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        lessonPlan.setText(person.getLessonPlan().value);
        homework.setText(person.getHomeworkList().shortDescription());
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
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
        TimeSlotCard card = (TimeSlotCard) other;
        return sessionTime.getText().equals(card.sessionTime.getText())
                && person.equals(card.person);
    }
}

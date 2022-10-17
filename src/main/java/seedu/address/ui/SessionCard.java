package seedu.address.ui;

import java.util.Comparator;

import javax.swing.plaf.synth.Region;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Session}.
 */
public class SessionCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml"; // to be changed

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label session;
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


    public SessionCard(Person person) {
        super(FXML);
        this.person = person;
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
        SessionCard card = (SessionCard) other;
        return session.getText().equals(card.session.getText())
                && person.equals(card.person);
    }
}

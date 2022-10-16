package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String DOT = ". ";
    private static final String COMMA = ", ";

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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label income;
    @FXML
    private Label monthly;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane appointments;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        addTagLabels(person);
        addAppointmentLabels(person);
    }

    private void addAppointmentLabels(Person person) {
        List<Appointment> appointmentList = person.getAppointments().stream()
                .sorted(Comparator.comparing(appointment -> appointment.getDateTime())).collect(Collectors.toList());
        int listSize = appointmentList.size();
        for (int i = 0; i < listSize; i++) {
            appointments.getChildren().add(new Label(i + 1 + DOT + appointmentList.get(i).toString()
                    + "                                                                                 "));
        }
    }

    private void addTagLabels(Person person) {
        income.setText(person.getIncome().value);
        monthly.setText(person.getMonthly().value);
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
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

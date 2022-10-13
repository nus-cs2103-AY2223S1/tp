package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Schedule}.
 */
public class ScheduleCard extends UiPart<Region> {

    private static final String FXML = "ScheduleListCard.fxml";

    private static final String COLOUR_OF_DEBTOR = "-fx-text-fill: #FF0000;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    @FXML
    private HBox scheduleCardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label classTime;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public ScheduleCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        setWarningIfOwed(person);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        classTime.setText(person.getAClass().toTimeString());
    }

    /**
     * Sets off warning indication if the person owes money.
     *
     * @param person to check whether he/she is a debtor
     */
    private void setWarningIfOwed(Person person) {
        if (person.owesMoney()) {
            String nameWithAmount = person.getName().fullName + " - To collect $" + person.getMoneyOwed().value;
            name.setText(nameWithAmount);
            name.setStyle(COLOUR_OF_DEBTOR);
        } else {
            name.setText(person.getName().fullName);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCard)) {
            return false;
        }

        // state check
        ScheduleCard card = (ScheduleCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}

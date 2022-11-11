package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;
    private int displayedIndex;
    private CommandBox.CommandExecutor commandExecutor;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label patientType;
    @FXML
    private Label upcomingAppointment;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, CommandBox.CommandExecutor commandExecutor) {
        super(FXML);
        this.person = person;
        this.displayedIndex = displayedIndex;
        this.commandExecutor = commandExecutor;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        patientType.setText(person.getPatientType().toString());
        person.getUpcomingAppointment().ifPresentOrElse(ua -> upcomingAppointment.setText(ua.toString()), () ->
                upcomingAppointment.setVisible(false));
    }

    @FXML
    public void handleViewPerson() throws CommandException, ParseException {
        commandExecutor.execute("view " + displayedIndex);
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

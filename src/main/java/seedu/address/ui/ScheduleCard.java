package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.student.Student;

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

    public final Student student;
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
    @FXML
    private Label markStatus;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public ScheduleCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        phone.setText(student.getPhone().value);
        address.setText(student.getAddress().value);
        classTime.setText(student.getDisplayedClass().toTimeString());
        setWarningIfOwed(student);
        setMarkStatus(student);
    }

    /**
     * Sets off warning indication if the student owes money.
     *
     * @param student to set warning if he/she owes money.
     */
    private void setWarningIfOwed(Student student) {
        if (student.isOwingMoney()) {
            String nameWithAmount = student.getName().fullName + " - To collect $" + student.getMoneyOwed().value;
            name.setText(nameWithAmount);
            name.setStyle(COLOUR_OF_DEBTOR);
        } else {
            name.setText(student.getName().fullName);
        }
    }

    /**
     * Toggles the mark indicator based on the status of the student.
     *
     * @param student to check whether he/she is marked.
     */
    private void setMarkStatus(Student student) {
        if (student.getMarkStatus().isMarked()) {
            markStatus.setText("[X]");
        } else {
            markStatus.setText("[  ]");
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
                && student.equals(card.student);
    }
}

package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.internship.Internship;
import seedu.address.model.person.PersonId;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

    static final String NO_CONTACT_PERSON = "No contact person";
    private static final String FXML = "InternshipListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Internship internship;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label companyName;
    @FXML
    private Label id;
    @FXML
    private Label role;
    @FXML
    private FlowPane status;
    @FXML
    private Label contactPerson;
    @FXML
    private Label interviewDate;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);
        this.internship = internship;
        id.setText(displayedIndex + ". ");
        name.setText(internship.getDisplayName());
        companyName.setText(internship.getCompanyName().fullName);
        role.setText(internship.getInternshipRole().roleName);
        String internshipStatus = internship.getInternshipStatus().toString();
        Label statusLabel = new Label(internshipStatus);
        statusLabel.getStyleClass().add(internshipStatus.toLowerCase());
        status.getChildren().add(statusLabel);
        contactPerson.setText(NO_CONTACT_PERSON); // dummy value
    }

    public PersonId getContactPersonId() {
        return internship.getContactPersonId();
    }

    public void setContactPerson(String contactPersonName) {
        if (contactPersonName == null) {
            contactPerson.setText(NO_CONTACT_PERSON);
        } else {
            contactPerson.setText("Contact Person: " + contactPersonName);
        }
        interviewDate.setText("Interview on: " + internship.getInterviewDate().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipCard)) {
            return false;
        }

        // state check
        InternshipCard card = (InternshipCard) other;
        return id.getText().equals(card.id.getText())
                && internship.equals(card.internship);
    }
}

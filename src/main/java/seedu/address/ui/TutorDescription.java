package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.tutor.Tutor;


/**
 * An UI component that displays information of a {@code Tutor}.
 */
public class TutorDescription extends UiPart<Region> {

    private static final String FXML = "TutorDescription.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tutor tutor;
    @FXML
    protected HBox cardPane;
    @FXML
    protected Label name;
    @FXML
    protected Label id;
    @FXML
    protected Label phone;
    @FXML
    protected Label address;
    @FXML
    protected Label email;
    @FXML
    protected Label institution;
    @FXML
    protected Label qualification;
    @FXML
    protected FlowPane tags;
    @FXML
    protected VBox belongedClassList;

    /**
     * Creates a {@code TutorCode} with the given {@code Tutor} and index to display.
     */
    public TutorDescription(Tutor tutor, int displayedIndex) {
        super(FXML);
        this.tutor = tutor;
        id.setText(displayedIndex + ". ");
        name.setText(tutor.getName().fullName);
        phone.setText(tutor.getPhone().value);
        address.setText(tutor.getAddress().value);
        email.setText(tutor.getEmail().value);
        institution.setText(tutor.getInstitution().institution);
        qualification.setText(tutor.getQualification().qualification);
        tutor.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tutor.getTuitionClasses().stream()
                .sorted(Comparator.comparing(tuitionClass -> tuitionClass.getName().name))
                .forEach(tuitionClass -> belongedClassList.getChildren().add(
                        new AssignedClass(tuitionClass.getName().name).getRoot()));
    }

    public Tutor getDisplayedTutor() {
        return this.tutor;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorDescription)) {
            return false;
        }

        // state check
        TutorDescription card = (TutorDescription) other;
        return id.getText().equals(card.id.getText())
                && tutor.equals(card.tutor);
    }
}

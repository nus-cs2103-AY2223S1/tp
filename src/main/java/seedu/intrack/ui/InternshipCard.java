package seedu.intrack.ui;

import java.util.Comparator;

import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Task;

/**
 * An UI component that displays information of a {@code Internship}.
 */
public class InternshipCard extends UiPart<Region> {

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
    private Label position;
    @FXML
    private Label id;
    @FXML
    private FlowPane status;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane tasks;
    @FXML
    private FlowPane tags;
    @FXML
    private Label remark;

    /**
     * Creates a {@code InternshipCode} with the given {@code Internship} and index to display.
     */
    public InternshipCard(Internship internship, int displayedIndex) {
        super(FXML);

        Label lab = new Label(internship.getStatus().toString());
        PseudoClass rejected = PseudoClass.getPseudoClass("rejected");
        lab.pseudoClassStateChanged(rejected, (internship.getStatus().toString()).equals("Rejected"));
        PseudoClass offered = PseudoClass.getPseudoClass("offered");
        lab.pseudoClassStateChanged(offered, (internship.getStatus().toString()).equals("Offered"));

        this.internship = internship;
        id.setText(displayedIndex + ". ");
        name.setText(internship.getName().fullName);
        position.setText(internship.getPosition().positionName);
        status.getChildren().add(lab);
        phone.setText(internship.getPhone().value);
        address.setText(internship.getAddress().value);
        email.setText(internship.getEmail().value);
        internship.getTasks().stream()
                .sorted(Comparator.comparing(task -> task.taskTime))
                .forEach(task -> tasks.getChildren().add(new Label(task.taskName + " at "
                        + task.taskTime.format(Task.FORMATTER))));
        internship.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        remark.setText(internship.getRemark().value);
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

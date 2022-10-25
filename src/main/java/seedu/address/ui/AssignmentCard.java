package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Assignments}.
 */
public class AssignmentCard extends UiPart<Region> {

    private static final String FXML = "AssignmentListCard.fxml";

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
    private FlowPane assignments;

    /**
     * A UI component that displays information of a {@code Assignment}.
     */
    public AssignmentCard(Person person, int displayedIndex) {
        super(FXML);
        this.cardPane.setStyle(String.format("-fx-border-color:%s ; -fx-border-width: 0 0 0 5;",
            getColourFromWorkload(person.getWorkloadScore())));
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        person.getAssignments()
                .forEach((key, value) -> {
                    String input = "";
                    int a = 1;
                    for (int i = 0; i < value.size(); i++) {
                        input += a + ". " + value.get(i) + "\n";
                        a++;
                    }
                    String assignmentString = key + ": \n" + input;
                    assignments.getChildren().add(new Label(assignmentString));
                });
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AssignmentCard)) {
            return false;
        }

        // state check
        AssignmentCard card = (AssignmentCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }

    private String getColourFromWorkload(int score) {
        if (score < 5) {
            return "green";
        } else if (score >= 5 && score < 15) {
            return "yellow";
        } else {
            return "red";
        }
    }

}

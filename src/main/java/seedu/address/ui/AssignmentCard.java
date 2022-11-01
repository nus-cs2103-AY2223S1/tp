package seedu.address.ui;

import java.awt.Color;

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
        Color color = getColourFromWorkload(person.getWorkloadScore());
        String colorString = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");";
        this.cardPane.setStyle(String.format("-fx-border-color:%s ; -fx-border-width: 0 0 0 5;",
                colorString));
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        // this.assignments.setOrientation(Orientation.HORIZONTAL);
        person.getAssignments()
                .forEach((key, value) -> {
                    String input = "";
                    int a = 1;
                    for (int i = 0; i < value.size(); i++) {
                        input += a + ". " + value.get(i) + "\n";
                        a++;
                    }
                    String assignmentString = key + ": \n" + input;
                    Label label = new Label(assignmentString);

                    label.setWrapText(true);
                    label.setMaxWidth(500);

                    assignments.getChildren().add(label);
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

    private Color getColourFromWorkload(int score) {
        //gradual fade for RGB
        double red = 255 * Math.sqrt(Math.sin(score * Math.PI / 200));
        double green = 255 * Math.sqrt(Math.cos(score * Math.PI / 200));;
        Color myColor;
        if (red >= 255 || green <= 0) {
            myColor = new Color(255, 0, 0);
        } else {
            myColor = new Color((int) red, (int) green, 0);
        }
        return myColor;
    }

}

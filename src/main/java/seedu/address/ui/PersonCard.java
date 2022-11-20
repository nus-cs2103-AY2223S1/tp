package seedu.address.ui;

import java.awt.Color;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonGroup;

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
    private FlowPane tags;
    @FXML
    private FlowPane personGroup;

    @FXML
    private HBox phoneContainer;
    @FXML
    private HBox addressContainer;
    @FXML
    private HBox emailContainer;


    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        Color color = getColourFromWorkload(person.getWorkloadScore());
        String colorString = "rgb(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ");";
        this.cardPane.setStyle(String.format("-fx-border-color:%s ; -fx-border-width: 0 0 0 5;",
                colorString));
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> {
                    Label label = new Label(tag.tagName);
                    label.setWrapText(true);
                    label.setMaxWidth(550);
                    tags.getChildren().add(label);
                });

        person.getPersonGroups().stream()
                .sorted(Comparator.comparing(PersonGroup::getGroupName))
                .forEach(group -> {
                    Label label = new Label(group.getGroupName());
                    label.setWrapText(true);
                    label.setMaxWidth(550);
                    personGroup.getChildren().add(label);
                });
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

    private Color getColourFromWorkload(int score) {
        //Formula below for gradual fade of RGB adapted from
        //https://stackoverflow.com/questions/340209/generate-colors-between-red-and-green-for-a-power-meter
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

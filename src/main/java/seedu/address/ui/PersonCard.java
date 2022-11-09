package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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

    @FXML
    private HBox cardPane;
    @FXML
    private Label names;
    @FXML
    private Label id;
    @FXML
    private Label information;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane socials;
    @FXML
    private FlowPane servers;
    @FXML
    private FlowPane gameTypes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {

        super(FXML);
        this.person = person;

        id.setText(displayedIndex + ". ");
        names.setText(person.getMinecraftName().toString() + " [" + person.getName().toString() + "]");
        information.setText(person.toDisplayString());

        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        person.getSocials().stream()
                .forEach(soc -> socials.getChildren().add(new Label(soc.toString())));

        person.getServers().stream()
                .forEach(ser -> servers.getChildren().add(new Label(ser.toString())));

        person.getGameType().stream()
                .forEach(gam -> gameTypes.getChildren().add(new Label(gam.toString())));
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

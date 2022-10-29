package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
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
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label githubUsernameLabel;

    @FXML
    private Label informationLabel;

    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        idLabel.setText(displayedIndex + ". ");
        nameLabel.setText(person.getName().fullName);

        if (person.getGithubUser().isPresent()) {
            githubUsernameLabel.setText("@" + person.getGithubUser().get().getUsername());
        }

        ArrayList<String> information = new ArrayList<>();

        person.getRole().ifPresent(r -> information.add(r.toString()));
        person.getTimezone().ifPresent(t -> information.add(t.toString()));
        person.getAddress().ifPresent(a -> information.add(a.toString()));

        setLabelVisibility(informationLabel, information.size() != 0);
        if (information.size() > 0) {
            informationLabel.setText(String.join(" • ", information));
        }

        if (!person.getTags().isEmpty()) {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            tags.setManaged(false);
        }
    }

    private void setLabelVisibility(Label label, boolean visible) {
        // Remove node from tree so it doesn't occupy the space.
        // @see https://stackoverflow.com/a/28559958
        label.setManaged(visible);
        label.setVisible(visible);
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
        return idLabel.getText().equals(card.idLabel.getText())
            && person.equals(card.person);
    }
}

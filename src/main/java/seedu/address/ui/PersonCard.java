package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
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

    public final String githubUrl;
    private Person person;

    @FXML
    private Button copyButton;
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
    private Label github;
    @FXML
    private Label currModuleDescription;
    @FXML
    private Label prevModuleDescription;
    @FXML
    private Label planModuleDescription;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane currModulesTags;
    @FXML
    private FlowPane prevModulesTags;
    @FXML
    private FlowPane planModulesTags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        assert person.getPhone().value.isEmpty() == false;
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        assert person.getEmail().value.contains("@") == true;
        github.setText("Github: " + person.getGithub().value);
        currModuleDescription.setText("Current Modules: ");
        prevModuleDescription.setText("Previous Modules: ");
        planModuleDescription.setText("Planned Modules: ");
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        person.getCurrModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> currModulesTags.getChildren().add(new Label(mod.moduleName)));
        person.getPrevModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> prevModulesTags.getChildren().add(new Label(mod.moduleName)));
        person.getPlanModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> planModulesTags.getChildren().add(new Label(mod.moduleName)));
        githubUrl = "https://github.com/" + person.getGithub().value;
    }

    /**
     * Copies the URL to the github profile to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(githubUrl);
        clipboard.setContent(url);
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

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
    private Label moduleCode;
    @FXML
    private Label email;
    @FXML
    private Label telegram;
    @FXML
    private FlowPane tags;
    @FXML
    private HBox phoneContainer;
    @FXML
    private HBox emailContainer;
    @FXML
    private HBox telegramContainer;
    @FXML
    private HBox moduleContainer;
    @FXML
    private Button phoneCopyButton;
    @FXML
    private Button telegramCopyButton;
    @FXML
    private Button emailCopyButton;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        name.setWrapText(true);

        moduleCode.setText(person.getModuleCode().moduleCode);
        moduleCode.setWrapText(true);

        if (person.getPhone().value == null) {
            phoneCopyButton.setVisible(false);
            phoneCopyButton.setManaged(false);
        }
        phone.setText(person.getPhone().value);
        phone.setWrapText(true);

        if (person.getEmail().value == null) {
            emailCopyButton.setVisible(false);
            emailCopyButton.setManaged(false);
        }
        email.setText(person.getEmail().value);
        email.setWrapText(true);

        if (person.getTelegram().value == null) {
            telegramCopyButton.setVisible(false);
            telegramCopyButton.setManaged(false);
        }
        telegram.setText(person.getTelegram().value);
        telegram.setWrapText(true);

        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @FXML
    private void copyEmail() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent email = new ClipboardContent();
        email.putString(this.email.getText());
        clipboard.setContent(email);
    }

    @FXML
    private void copyTelegram() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent telegram = new ClipboardContent();
        telegram.putString(this.telegram.getText());
        clipboard.setContent(telegram);
    }

    @FXML
    private void copyPhone() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent phone = new ClipboardContent();
        phone.putString(this.phone.getText());
        clipboard.setContent(phone);
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

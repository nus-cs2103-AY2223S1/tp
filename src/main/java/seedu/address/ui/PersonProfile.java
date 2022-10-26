package seedu.address.ui;

import java.io.IOException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.FileUtil;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonProfile extends UiPart<Region> {
    public static final String EMPTY_DISPLAY_VALUE = "-";
    private static final String FXML = "PersonProfile.fxml";
    private static final String FILE_EXISTS = "Client Information";
    private static final String NO_FILE_EXISTS = "No File Exists";
    private static final String VALID_BUTTON_BGCOLOUR =
            "-fx-background-color: -fx-background-color: #1d1d1d;";
    private static final String INVALID_BUTTON_BGCOLOUR =
            "-fx-background-color: derive(red, 30%);";
    public final Person person;

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private HBox profilePane;
    @FXML
    private Label name;
    @FXML
    private Label name_label;
    @FXML
    private Label phone;
    @FXML
    private Label phone_label;
    @FXML
    private Label address;
    @FXML
    private Label address_label;
    @FXML
    private Label email;
    @FXML
    private Label email_label;
    @FXML
    private Label description;
    @FXML
    private Label description_label;
    @FXML
    private FlowPane tags;
    @FXML
    private Label netWorth;
    @FXML
    private Label netWorth_label;
    @FXML
    private FlowPane meetingTimes;
    @FXML
    private Button personFileButton;
    @FXML
    private Label buttonErrorMessage;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonProfile(Person person) {
        super(FXML);
        this.person = person;
        setNameField();
        setPhoneField();
        setAddressField();
        setEmailField();
        setDescriptionField();
        setNetWorthField();
        setMeetingsField();
        setTagsField();
    }

    private void setNameField() {
        if (person.getName().isEmpty()) {
            name.setManaged(false);
            name_label.setManaged(false);
            return;
        }
        name.setText(person.getName().getFullDisplayName());
    }

    private void setPhoneField() {
        if (person.getPhone().isEmpty()) {
            phone.setManaged(false);
            phone_label.setManaged(false);
            return;
        }
        phone.setText(person.getPhone().getDisplayValue());
    }

    private void setAddressField() {
        if (person.getAddress().isEmpty()) {
            address.setManaged(false);
            address_label.setManaged(false);
            return;
        }
        address.setText(person.getAddress().getDisplayValue());
    }

    private void setEmailField() {
        if (person.getEmail().isEmpty()) {
            email.setManaged(false);
            email_label.setManaged(false);
            return;
        }
        email.setText(person.getEmail().getDisplayValue());
    }

    private void setDescriptionField() {
        if (person.getDescription().isEmpty()) {
            description.setManaged(false);
            description_label.setManaged(false);
            return;
        }
        description.setText(person.getDescription().getDisplayValue());
    }

    private void setNetWorthField() {
        if (person.getNetWorth().isEmpty()) {
            netWorth.setManaged(false);
            netWorth_label.setManaged(false);
            return;
        }
        netWorth.setText(person.getNetWorth().getDisplayValue());
    }

    private void setMeetingsField() {
        person.getMeetingTimes().stream()
                .sorted(Comparator.comparing(meetingTime -> meetingTime.displayValue))
                .forEach(meetingTime -> meetingTimes.getChildren().add(new Label(meetingTime.displayValue)));
    }

    private void setTagsField() {
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Opens pdf file stored in person object.
     */
    @FXML
    private void openPersonFile() {
        try {
            FileUtil.openPdfFile(person.getFilePath().toString());
            showValidFilePathButton();
            if (buttonErrorMessage.isManaged()) {
                hideButtonErrorMessage();
            }
        }
        catch (IOException e) {
            showInvalidFilePathButton();
            showButtonErrorMessage(e);
        }
    }

    private void showValidFilePathButton() {
        personFileButton.setStyle(VALID_BUTTON_BGCOLOUR);
        personFileButton.setText(FILE_EXISTS);
    }

    private void showInvalidFilePathButton() {
        personFileButton.setStyle(INVALID_BUTTON_BGCOLOUR);
        personFileButton.setText(NO_FILE_EXISTS);
    }

    private void hideButtonErrorMessage() {
        buttonErrorMessage.setManaged(false);
    }

    private void showButtonErrorMessage(IOException e) {
        buttonErrorMessage.setText("Error: " + e.getMessage());
        buttonErrorMessage.setManaged(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonProfile)) {
            return false;
        }

        // state check
        PersonProfile profile = (PersonProfile) other;
        return person.equals(profile.person);
    }
}

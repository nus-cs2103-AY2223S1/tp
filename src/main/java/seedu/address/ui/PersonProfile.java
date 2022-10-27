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
    private Label name_Label;
    @FXML
    private Label phone;
    @FXML
    private Label phone_Label;
    @FXML
    private Label address;
    @FXML
    private Label address_Label;
    @FXML
    private Label email;
    @FXML
    private Label email_Label;
    @FXML
    private Label description;
    @FXML
    private Label description_Label;
    @FXML
    private Label tags_Label;
    @FXML
    private FlowPane tags;
    @FXML
    private Label netWorth;
    @FXML
    private Label netWorth_Label;
    @FXML
    private Label meeting_Label;
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
        setPersonFileButton();
    }

    /**
     * Sets name value in name if value is not empty.
     */
    private void setNameField() {
        if (person.getName().isEmpty()) {
            name.setManaged(false);
            name_Label.setManaged(false);
            return;
        }
        name.setText(person.getName().getFullDisplayName());
    }

    /**
     * Sets phone value in phone if value is not empty.
     */
    private void setPhoneField() {
        if (person.getPhone().isEmpty()) {
            phone.setManaged(false);
            phone_Label.setManaged(false);
            return;
        }
        phone.setText(person.getPhone().getDisplayValue());
    }

    /**
     * Sets address value in address if value is not empty.
     */
    private void setAddressField() {
        if (person.getAddress().isEmpty()) {
            address.setManaged(false);
            address_Label.setManaged(false);
            return;
        }
        address.setText(person.getAddress().getDisplayValue());
    }

    /**
     * Sets email value in email if value is not empty.
     */
    private void setEmailField() {
        if (person.getEmail().isEmpty()) {
            email.setManaged(false);
            email_Label.setManaged(false);
            return;
        }
        email.setText(person.getEmail().getDisplayValue());
    }

    /**
     * Sets description value in description if value is not empty.
     */
    private void setDescriptionField() {
        if (person.getDescription().isEmpty()) {
            description.setManaged(false);
            description_Label.setManaged(false);
            return;
        }
        description.setText(person.getDescription().getDisplayValue());
    }

    /**
     * Sets netWorth value in netWorth if value is not empty.
     */
    private void setNetWorthField() {
        if (person.getNetWorth().isEmpty()) {
            netWorth.setManaged(false);
            netWorth_Label.setManaged(false);
            return;
        }
        netWorth.setText(person.getNetWorth().getDisplayValue());
    }

    /**
     * Sets person meetingTimes in meetingTimes if meetingTimes is not empty.
     */
    private void setMeetingsField() {
        if (person.getMeetingTimes().isEmpty()) {
            meetingTimes.setManaged(false);
            meeting_Label.setManaged(false);
            return;
        }
        person.getMeetingTimes().stream()
                .sorted(Comparator.comparing(meetingTime -> meetingTime.displayValue))
                .forEach(meetingTime -> meetingTimes.getChildren().add(new Label(meetingTime.displayValue)));
    }

    /**
     * Sets person tags in tags if tags is not empty.
     */
    private void setTagsField() {
        if (person.getTags().isEmpty()) {
            tags.setManaged(false);
            tags_Label.setManaged(false);
            return;
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets personFileButton to unmanaged if filepath is empty.
     */
    private void setPersonFileButton() {
        if (person.getFilePath().isEmpty()) {
            personFileButton.setManaged(false);
            return;
        }
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
        } catch (IOException e) {
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

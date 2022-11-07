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
import seedu.address.model.tag.Tag;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonProfile extends UiPart<Region> {
    private static final String FXML = "PersonProfile.fxml";
    private static final String FILE_EXISTS = "Client Information";
    private static final String NO_FILE_EXISTS = "No File Exists";
    private static final String EMPTY_FILEPATH = "No Client Information";
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
    private Label nameLabel;
    @FXML
    private Label phone;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label address;
    @FXML
    private Label addressLabel;
    @FXML
    private Label email;
    @FXML
    private Label emailLabel;
    @FXML
    private Label description;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label tagsLabel;
    @FXML
    private FlowPane tags;
    @FXML
    private Label netWorth;
    @FXML
    private Label netWorthLabel;
    @FXML
    private Label meetingLabel;
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
        setProfileFields();
    }

    /**
     * Sets all the fields in person profile.
     */
    private void setProfileFields() {
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
            nameLabel.setManaged(false);
            return;
        }
        name.setText(person.getName().getFullName());
    }

    /**
     * Sets phone value in phone if value is not empty.
     */
    private void setPhoneField() {
        if (person.getPhone().isEmpty()) {
            phone.setManaged(false);
            phoneLabel.setManaged(false);
            return;
        }
        phone.setText(person.getPhone().getValue());
    }

    /**
     * Sets address value in address if value is not empty.
     */
    private void setAddressField() {
        if (person.getAddress().isEmpty()) {
            address.setManaged(false);
            addressLabel.setManaged(false);
            return;
        }
        address.setText(person.getAddress().getValue());
    }

    /**
     * Sets email value in email if value is not empty.
     */
    private void setEmailField() {
        if (person.getEmail().isEmpty()) {
            email.setManaged(false);
            emailLabel.setManaged(false);
            return;
        }
        email.setText(person.getEmail().getValue());
    }

    /**
     * Sets description value in description if value is not empty.
     */
    private void setDescriptionField() {
        if (person.getDescription().isEmpty()) {
            description.setManaged(false);
            descriptionLabel.setManaged(false);
            return;
        }
        description.setText(person.getDescription().getValue());
    }

    /**
     * Sets netWorth value in netWorth if value is not empty.
     */
    private void setNetWorthField() {
        if (person.getNetWorth().isEmpty()) {
            netWorth.setManaged(false);
            netWorthLabel.setManaged(false);
            return;
        }
        netWorth.setText(person.getNetWorth().getValue());
    }

    /**
     * Sets person meetingTimes in meetingTimes if meetingTimes is not empty.
     */
    private void setMeetingsField() {
        if (person.getMeetingTimes().isEmpty()) {
            meetingTimes.setManaged(false);
            meetingLabel.setManaged(false);
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
            tagsLabel.setManaged(false);
            return;
        }
        Tag firstTag = person.getTags().iterator().next();
        setTagStyle(firstTag);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Sets personFileButton to unmanaged if filepath is empty.
     */
    private void setPersonFileButton() {
        if (person.getFilePath().isEmpty()) {
            personFileButton.setText(EMPTY_FILEPATH);
            personFileButton.setDisable(true);
            return;
        }
    }

    /**
     * Sets style of tags based on potential or secured client.
     * @param firstTag first tag in Tag Set
     */
    public void setTagStyle(Tag firstTag) {
        if (firstTag.isPotential()) {
            tags.setId("potentialTags");
        } else if (firstTag.isSecured()) {
            tags.setId("securedTags");
        }
    }

    /**
     * Opens pdf file stored in person object.
     */
    @FXML
    private void openPersonFile() {
        if (person.getFilePath().isEmpty()) {
            return;
        }
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

    /**
     * Shows the Client Information button.
     */
    private void showValidFilePathButton() {
        personFileButton.setStyle(VALID_BUTTON_BGCOLOUR);
        personFileButton.setText(FILE_EXISTS);
    }

    /**
     * Shows the no file exists button
     */
    private void showInvalidFilePathButton() {
        personFileButton.setStyle(INVALID_BUTTON_BGCOLOUR);
        personFileButton.setText(NO_FILE_EXISTS);
    }

    /**
     * Hides error message.
     */
    private void hideButtonErrorMessage() {
        buttonErrorMessage.setManaged(false);
    }

    /**
     * Shows error message.
     * @param e error message to be shown.
     */
    private void showButtonErrorMessage(IOException e) {
        buttonErrorMessage.setText("Error: " + e.getMessage());
        buttonErrorMessage.setManaged(true);
    }

    /**
     * Returns true if otherPerson is same as person in person profile.
     * @param otherPerson Person to check
     * @return True if is same person
     */
    public boolean isSamePerson(Person otherPerson) {
        return person.isSamePerson(otherPerson);
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

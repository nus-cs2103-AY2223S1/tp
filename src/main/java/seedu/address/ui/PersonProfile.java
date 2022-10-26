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
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label description;
    @FXML
    private FlowPane tags;
    @FXML
    private Label netWorth;
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
        name.setText(person.getName().getFullDisplayName());
        phone.setText(person.getPhone().getDisplayValue());
        address.setText(person.getAddress().getDisplayValue());
        email.setText(person.getEmail().getDisplayValue());
        description.setText(person.getDescription().getDisplayValue());
        netWorth.setText(person.getNetWorth().getDisplayValue());
        person.getMeetingTimes().stream()
                .sorted(Comparator.comparing(meetingTime -> meetingTime.displayValue))
                .forEach(meetingTime -> meetingTimes.getChildren().add(new Label(meetingTime.displayValue)));
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

package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path tutorAddressBookFilePath = Paths.get("data" , "tutoraddressbook.json");
    private Path studentAddressBookFilePath = Paths.get("data", "studentaddressbook.json");
    private Path tuitionClassAddressBookFilePath = Paths.get("data", "tuitionclassaddressbook.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {}

    /**
     * Creates a {@code UserPrefs} with the prefs in {@code userPrefs}.
     */
    public UserPrefs(ReadOnlyUserPrefs userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefs} with {@code newUserPrefs}.
     */
    public void resetData(ReadOnlyUserPrefs newUserPrefs) {
        requireNonNull(newUserPrefs);
        setGuiSettings(newUserPrefs.getGuiSettings());
        setTutorAddressBookFilePath(newUserPrefs.getTutorAddressBookFilePath());
        setStudentAddressBookFilePath(newUserPrefs.getStudentAddressBookFilePath());
        setTuitionClassAddressBookFilePath(newUserPrefs.getTuitionClassAddressBookFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getTutorAddressBookFilePath() {
        return tutorAddressBookFilePath;
    }

    public Path getStudentAddressBookFilePath() {
        return studentAddressBookFilePath;
    }

    public Path getTuitionClassAddressBookFilePath() {
        return tuitionClassAddressBookFilePath;
    }

    public void setTutorAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.tutorAddressBookFilePath = addressBookFilePath;
    }

    public void setStudentAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.studentAddressBookFilePath = addressBookFilePath;
    }

    public void setTuitionClassAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        this.tuitionClassAddressBookFilePath = addressBookFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof UserPrefs)) { //this handles null as well.
            return false;
        }

        UserPrefs o = (UserPrefs) other;

        return guiSettings.equals(o.guiSettings)
                && tutorAddressBookFilePath.equals(o.tutorAddressBookFilePath)
                && studentAddressBookFilePath.equals(o.studentAddressBookFilePath)
                && tuitionClassAddressBookFilePath.equals(o.tuitionClassAddressBookFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, tutorAddressBookFilePath, studentAddressBookFilePath,
                tuitionClassAddressBookFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + tutorAddressBookFilePath);
        sb.append(", " + studentAddressBookFilePath);
        sb.append(", " + tuitionClassAddressBookFilePath);
        return sb.toString();
    }

}

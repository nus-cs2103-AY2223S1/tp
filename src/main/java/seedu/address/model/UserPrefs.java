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
    private Path personModelFilePath = Paths.get("data" , "personmodel.json");
    private Path propertyModelFilePath = Paths.get("data", "propertymodel.json");

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
        setPersonModelFilePath(newUserPrefs.getPersonModelFilePath());
        setPropertyModelFilePath(newUserPrefs.getPropertyModelFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPersonModelFilePath() {
        return personModelFilePath;
    }

    public void setPersonModelFilePath(Path personModelFilePath) {
        requireNonNull(personModelFilePath);
        this.personModelFilePath = personModelFilePath;
    }

    public Path getPropertyModelFilePath() {
        return propertyModelFilePath;
    }

    public void setPropertyModelFilePath(Path propertyModelFilePath) {
        requireNonNull(propertyModelFilePath);
        this.propertyModelFilePath = propertyModelFilePath;
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
                && personModelFilePath.equals(o.personModelFilePath)
                && propertyModelFilePath.equals(o.propertyModelFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, personModelFilePath, propertyModelFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nPersons data file location : " + personModelFilePath);
        sb.append("\nProperty data file location : " + propertyModelFilePath);
        return sb.toString();
    }

}

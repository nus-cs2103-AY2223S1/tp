package seedu.condonery.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.condonery.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path propertyDirectoryFilePath = Paths.get("data" , "propertyDirectory.json");
    private Path clientDirectoryFilePath = Paths.get("data" , "clientDirectory.json");
    private Path userImageDirectoryPath = Paths.get("data", "images");

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
        setPropertyDirectoryFilePath(newUserPrefs.getPropertyDirectoryFilePath());
        setClientDirectoryFilePath(newUserPrefs.getClientDirectoryFilePath());
        setUserImageDirectoryPath(newUserPrefs.getUserImageDirectoryPath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getPropertyDirectoryFilePath() {
        return propertyDirectoryFilePath;
    }

    @Override
    public Path getClientDirectoryFilePath() {
        return clientDirectoryFilePath;
    }

    @Override
    public Path getUserImageDirectoryPath() {
        return userImageDirectoryPath;
    }

    public void setPropertyDirectoryFilePath(Path propertyDirectoryFilePath) {
        requireNonNull(propertyDirectoryFilePath);
        this.propertyDirectoryFilePath = propertyDirectoryFilePath;
    }

    public void setClientDirectoryFilePath(Path clientDirectoryFilePath) {
        requireNonNull(clientDirectoryFilePath);
        this.clientDirectoryFilePath = clientDirectoryFilePath;
    }

    public void setUserImageDirectoryPath(Path userImageDirectoryPath) {
        requireNonNull(userImageDirectoryPath);
        this.userImageDirectoryPath = userImageDirectoryPath;
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
                && propertyDirectoryFilePath.equals(o.propertyDirectoryFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, propertyDirectoryFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + propertyDirectoryFilePath);
        return sb.toString();
    }

}

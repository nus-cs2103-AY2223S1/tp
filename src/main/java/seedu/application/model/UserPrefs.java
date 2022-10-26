package seedu.application.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.application.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path applicationBookFilePath = Paths.get("data" , "applicationbook.json");
    private SortSetting sortSetting = SortSetting.BY_DATE; // Applications sorted by date by default

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
        setApplicationBookFilePath(newUserPrefs.getApplicationBookFilePath());
        setSortSetting(newUserPrefs.getSortSetting());
    }

    @Override
    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    @Override
    public Path getApplicationBookFilePath() {
        return applicationBookFilePath;
    }

    public void setApplicationBookFilePath(Path applicationBookFilePath) {
        requireNonNull(applicationBookFilePath);
        this.applicationBookFilePath = applicationBookFilePath;
    }

    @Override
    public SortSetting getSortSetting() {
        return sortSetting;
    }

    public void setSortSetting(SortSetting sortSetting) {
        requireNonNull(sortSetting);
        this.sortSetting = sortSetting;
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
                && applicationBookFilePath.equals(o.applicationBookFilePath)
                && sortSetting.equals(o.sortSetting);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, applicationBookFilePath, sortSetting);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + applicationBookFilePath);
        sb.append("\nSort Setting: " + sortSetting);
        return sb.toString();
    }

}

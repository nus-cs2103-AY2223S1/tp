package soconnect.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import soconnect.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();
    private Path soConnectFilePath = Paths.get("data" , "soconnect.json");
    private Path todoListFilePath = Paths.get("data" , "todolist.json");

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
        setSoConnectFilePath(newUserPrefs.getSoConnectFilePath());
        setTodoListFilePath(newUserPrefs.getTodoListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public String getAttributeOrder() {
        return guiSettings.getAttributeOrder();
    }

    public String getHiddenAttributes() {
        return guiSettings.getHiddenAttributes();
    }

    public Path getSoConnectFilePath() {
        return soConnectFilePath;
    }

    public void setSoConnectFilePath(Path soConnectFilePath) {
        requireNonNull(soConnectFilePath);
        this.soConnectFilePath = soConnectFilePath;
    }

    public Path getTodoListFilePath() {
        return todoListFilePath;
    }

    public void setTodoListFilePath(Path todoListFilePath) {
        requireNonNull(todoListFilePath);
        this.todoListFilePath = todoListFilePath;
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
                && soConnectFilePath.equals(o.soConnectFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, soConnectFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + soConnectFilePath);
        return sb.toString();
    }

}

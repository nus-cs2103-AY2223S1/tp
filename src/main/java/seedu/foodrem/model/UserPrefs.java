package seedu.foodrem.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.foodrem.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 * @author
 */
public class UserPrefs implements ReadOnlyUserPrefs {
    private GuiSettings guiSettings = new GuiSettings();
    private Path foodRemFilePath = Paths.get("data", "foodrem.json");

    /**
     * Creates a {@code UserPrefs} with default values.
     */
    public UserPrefs() {
    }

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
        setFoodRemFilePath(newUserPrefs.getFoodRemFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getFoodRemFilePath() {
        return foodRemFilePath;
    }

    public void setFoodRemFilePath(Path foodRemFilePath) {
        requireNonNull(foodRemFilePath);
        this.foodRemFilePath = foodRemFilePath;
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
                && foodRemFilePath.equals(o.foodRemFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, foodRemFilePath);
    }

    @Override
    public String toString() {
        return "Gui Settings : " + guiSettings + "\nLocal data file location : " + foodRemFilePath;
    }
}

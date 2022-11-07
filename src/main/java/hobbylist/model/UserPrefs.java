package hobbylist.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import hobbylist.commons.core.AliasSettings;
import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.ThemeSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefs implements ReadOnlyUserPrefs {

    private GuiSettings guiSettings = new GuiSettings();

    private ThemeSettings themeSettings = new ThemeSettings();

    private AliasSettings aliasSettings = new AliasSettings();

    private Path hobbyListFilePath = Paths.get("data" , "hobbylist.json");

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
        setThemeSettings(newUserPrefs.getThemeSettings());
        setAliasSettings(newUserPrefs.getAliasSettings());
        setHobbyListFilePath(newUserPrefs.getHobbyListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public AliasSettings getAliasSettings() {
        return aliasSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public ThemeSettings getThemeSettings() {
        return themeSettings;
    }

    public void setThemeSettings(ThemeSettings themeSettings) {
        requireNonNull(themeSettings);
        this.themeSettings = themeSettings;
    }

    public void setAliasSettings(AliasSettings aliasSettings) {
        requireNonNull(aliasSettings);
        this.aliasSettings = aliasSettings;
    }

    public Path getHobbyListFilePath() {
        return hobbyListFilePath;
    }

    public void setHobbyListFilePath(Path hobbyListFilePath) {
        requireNonNull(hobbyListFilePath);
        this.hobbyListFilePath = hobbyListFilePath;
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
                && aliasSettings.equals(o.aliasSettings)
                && hobbyListFilePath.equals(o.hobbyListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, aliasSettings, hobbyListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nTheme Settings : " + themeSettings);
        sb.append("\nAlias Settings : " + aliasSettings);
        sb.append("\nLocal data file location : " + hobbyListFilePath);
        return sb.toString();
    }

}

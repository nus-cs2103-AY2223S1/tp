package hobbylist.model;

import java.nio.file.Path;

import hobbylist.commons.core.AliasSettings;
import hobbylist.commons.core.GuiSettings;
import hobbylist.commons.core.ThemeSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    ThemeSettings getThemeSettings();

    AliasSettings getAliasSettings();

    Path getHobbyListFilePath();

}

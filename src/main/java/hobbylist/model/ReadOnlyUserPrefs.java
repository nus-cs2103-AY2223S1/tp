package hobbylist.model;

import java.nio.file.Path;

import hobbylist.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getHobbyListFilePath();

}

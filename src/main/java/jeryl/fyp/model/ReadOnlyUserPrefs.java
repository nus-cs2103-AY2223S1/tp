package jeryl.fyp.model;

import java.nio.file.Path;

import jeryl.fyp.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFypManagerFilePath();

}

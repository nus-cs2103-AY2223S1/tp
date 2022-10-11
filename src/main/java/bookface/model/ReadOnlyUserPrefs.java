package bookface.model;

import java.nio.file.Path;

import bookface.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBookFaceFilePath();

}

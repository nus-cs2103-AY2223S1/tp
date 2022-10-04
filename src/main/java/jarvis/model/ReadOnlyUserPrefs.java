package jarvis.model;

import java.nio.file.Path;

import jarvis.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudentBookFilePath();

}

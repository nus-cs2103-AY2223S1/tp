package taskbook.model;

import java.nio.file.Path;

import taskbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskBookFilePath();

}

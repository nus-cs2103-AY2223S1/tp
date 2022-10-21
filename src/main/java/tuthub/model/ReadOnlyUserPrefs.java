package tuthub.model;

import java.nio.file.Path;

import tuthub.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTuthubFilePath();

}

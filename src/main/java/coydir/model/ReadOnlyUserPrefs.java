package coydir.model;

import java.nio.file.Path;

import coydir.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getDatabaseFilePath();

}

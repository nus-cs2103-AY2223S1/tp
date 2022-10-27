package seedu.boba.model;

import java.nio.file.Path;

import seedu.boba.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getBobaBotFilePath();

}

package seedu.watson.model;

import java.nio.file.Path;

import seedu.watson.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getDatabaseFilePath();

}

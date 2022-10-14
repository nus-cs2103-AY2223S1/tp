package seedu.application.model;

import java.nio.file.Path;

import seedu.application.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getApplicationBookFilePath();

}

package seedu.studmap.model;

import java.nio.file.Path;

import seedu.studmap.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudMapFilePath();

}

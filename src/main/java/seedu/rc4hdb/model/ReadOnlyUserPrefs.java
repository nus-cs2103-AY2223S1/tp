package seedu.rc4hdb.model;

import java.nio.file.Path;

import seedu.rc4hdb.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getResidentBookFilePath();

}

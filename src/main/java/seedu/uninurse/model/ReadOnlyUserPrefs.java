package seedu.uninurse.model;

import java.nio.file.Path;

import seedu.uninurse.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getUninurseBookFilePath();

}

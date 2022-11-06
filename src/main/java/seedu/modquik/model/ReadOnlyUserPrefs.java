package seedu.modquik.model;

import java.nio.file.Path;

import seedu.modquik.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getModQuikFilePath();

}

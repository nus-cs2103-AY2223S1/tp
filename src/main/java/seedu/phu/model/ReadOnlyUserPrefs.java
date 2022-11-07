package seedu.phu.model;

import java.nio.file.Path;

import seedu.phu.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getInternshipBookFilePath();

}

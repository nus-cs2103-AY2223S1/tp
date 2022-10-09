package seedu.workbook.model;

import java.nio.file.Path;

import seedu.workbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getWorkBookFilePath();

}

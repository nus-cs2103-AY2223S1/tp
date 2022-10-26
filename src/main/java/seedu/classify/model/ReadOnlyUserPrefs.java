package seedu.classify.model;

import java.nio.file.Path;

import seedu.classify.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getStudentRecordFilePath();

}

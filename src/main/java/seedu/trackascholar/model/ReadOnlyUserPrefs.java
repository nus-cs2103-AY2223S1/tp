package seedu.trackascholar.model;

import java.nio.file.Path;

import seedu.trackascholar.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTrackAScholarFilePath();

}

package seedu.intrack.model;

import java.nio.file.Path;

import seedu.intrack.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getInTrackFilePath();

}

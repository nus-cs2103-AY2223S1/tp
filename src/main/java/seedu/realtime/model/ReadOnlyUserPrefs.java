package seedu.realtime.model;

import java.nio.file.Path;

import seedu.realtime.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getRealTimeFilePath();

}

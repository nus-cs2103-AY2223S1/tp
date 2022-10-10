package seedu.waddle.model;

import java.nio.file.Path;

import seedu.waddle.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getWaddleFilePath();

}

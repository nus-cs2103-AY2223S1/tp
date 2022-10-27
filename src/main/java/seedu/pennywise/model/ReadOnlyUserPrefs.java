package seedu.pennywise.model;

import java.nio.file.Path;

import seedu.pennywise.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPennyWiseFilePath();

}

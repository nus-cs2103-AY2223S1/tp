package seedu.pennyWise.model;

import java.nio.file.Path;

import seedu.pennyWise.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPennyWiseFilePath();

}

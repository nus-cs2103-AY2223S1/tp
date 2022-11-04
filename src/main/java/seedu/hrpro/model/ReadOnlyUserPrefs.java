package seedu.hrpro.model;

import java.nio.file.Path;

import seedu.hrpro.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getHrProFilePath();

}

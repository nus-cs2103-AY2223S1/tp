package seedu.masslinkers.model;

import java.nio.file.Path;

import seedu.masslinkers.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMassLinkersFilePath();

}

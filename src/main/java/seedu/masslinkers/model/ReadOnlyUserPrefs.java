package seedu.masslinkers.model;

import java.nio.file.Path;

import seedu.masslinkers.commons.core.GuiSettings;

//@@author
/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMassLinkersFilePath();

}

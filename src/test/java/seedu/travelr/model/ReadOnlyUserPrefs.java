package seedu.travelr.model;

import seedu.travelr.commons.core.GuiSettings;

import java.nio.file.Path;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

}

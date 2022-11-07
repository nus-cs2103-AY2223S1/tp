package seedu.codeconnect.model;

import java.nio.file.Path;

import seedu.codeconnect.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getTaskListFilePath();
}

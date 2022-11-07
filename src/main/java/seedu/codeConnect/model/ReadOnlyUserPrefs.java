package seedu.codeConnect.model;

import java.nio.file.Path;

import seedu.codeConnect.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getTaskListFilePath();
}

package seedu.condonery.model;

import java.nio.file.Path;

import seedu.condonery.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPropertyDirectoryFilePath();

    Path getClientDirectoryFilePath();

    Path getUserImageDirectoryPath();
}

package tracko.model;

import java.nio.file.Path;

import tracko.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

    Path getTrackOFilePath();

}

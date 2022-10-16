package soconnect.model;

import java.nio.file.Path;

import soconnect.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getSoConnectFilePath();

    Path getTodoListFilePath();

}

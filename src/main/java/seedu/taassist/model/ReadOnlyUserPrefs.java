package seedu.taassist.model;

import java.nio.file.Path;

import seedu.taassist.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaAssistFilePath();

}

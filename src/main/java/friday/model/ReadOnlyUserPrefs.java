package friday.model;

import java.nio.file.Path;

import friday.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFridayFilePath();

}

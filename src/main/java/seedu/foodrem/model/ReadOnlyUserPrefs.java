package seedu.foodrem.model;

import java.nio.file.Path;

import seedu.foodrem.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFoodRemFilePath();

}

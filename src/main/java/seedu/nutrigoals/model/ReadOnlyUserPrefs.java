package seedu.nutrigoals.model;

import java.nio.file.Path;

import seedu.nutrigoals.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getNutriGoalsFilePath();

}

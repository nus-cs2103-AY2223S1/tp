package seedu.foodrem.model;

import java.nio.file.Path;

import seedu.foodrem.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    /**
     * Returns the gui settings of FoodRem.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the Path of FoodRem.
     */
    Path getFoodRemFilePath();
}

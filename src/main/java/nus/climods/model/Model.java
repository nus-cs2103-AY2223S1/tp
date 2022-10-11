package nus.climods.model;

import nus.climods.commons.core.GuiSettings;
import nus.climods.model.module.ReadOnlyModuleList;

/**
 * The API of the Model component.
 */
public interface Model {

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    ReadOnlyModuleList getModuleList();
}
